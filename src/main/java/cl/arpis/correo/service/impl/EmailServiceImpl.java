package cl.arpis.correo.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.MensajeEmailDto;
import cl.arpis.correo.dto.datos.CorreoDto;
import cl.arpis.correo.enums.TipoCorreoEnum;
import cl.arpis.correo.exceptions.ArpisException;
import cl.arpis.correo.exceptions.CorreoException;
import cl.arpis.correo.persistence.clientes.samsonite.entities.TemplateEntity;
import cl.arpis.correo.persistence.clientes.samsonite.repositories.TemplateRepository;
import cl.arpis.correo.service.EmailService;
import cl.arpis.correo.util.DateUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

	private Environment env;
	private TemplateRepository templateRepository;
	private ITemplateEngine templateEngine;

	public EmailServiceImpl(Environment env,
			TemplateRepository templateRepository,
			ITemplateEngine templateEngine) {
		this.env = env;
		this.templateRepository = templateRepository;
		this.templateEngine = templateEngine;
	}

	@Override
	public void enviarResultados(final MensajeDto correo, final ContenedorCorreoDto contCorreo) {
		this.enviarEmail(correo, MensajeEmailDto.builder().mensaje("").build(), contCorreo);
	}

	@Override
	public void enviarEmail(final MensajeDto correo, final MensajeEmailDto mensaje,
			final ContenedorCorreoDto contCorreos) {
		// Obtener casillas
		final CorreosEnvioDTO envio = this.clasificarCorreos(contCorreos, mensaje);
		// Obtener template
		final Optional<TemplateEntity> template = this.templateRepository.findById(envio.getIdTemplate());
		if(template.isPresent()) {
			this.enviarConTemplate(envio, correo, mensaje, template.get());
		} else {
			this.enviarSinTemplate(envio, correo, mensaje);
		}
	}

	private CorreosEnvioDTO clasificarCorreos(final ContenedorCorreoDto contCorreos,
			final MensajeEmailDto mensaje) {
		final Optional<CorreoDto> servicio = contCorreos.getListaCorreo().stream()
				.filter(c -> TipoCorreoEnum.SERVICIO.equals(c.getTipoCorreo().getNombre()))
				.map(c -> c.getCorreo())
				.findFirst();
		if(servicio.isEmpty()) {
			log.error(String.format("Sin cuenta de servicio para conectarse al SMTP: Mensaje %s", mensaje.toString()));
			throw new CorreoException("Sin cuenta de servicio para conectarse al SMTP");
		}
		final Optional<CorreoDto> emisor = contCorreos.getListaCorreo().stream()
				.filter(c -> TipoCorreoEnum.DE.equals(c.getTipoCorreo().getNombre()))
				.map(c -> c.getCorreo())
				.findFirst();
		if(emisor.isEmpty()) {
			log.error(String.format("Sin cuenta de emisor: Mensaje %s", mensaje.toString()));
			throw new CorreoException("Sin cuenta de emisor");
		}
		final List<CorreoDto> receptores = contCorreos.getListaCorreo().stream()
				.filter(c -> TipoCorreoEnum.PARA.equals(c.getTipoCorreo().getNombre()))
				.map(c -> c.getCorreo())
				.toList();
		if(receptores.isEmpty()) {
			log.error(String.format("Correo sin receptores: Mensaje %s", mensaje.toString()));
			throw new CorreoException("Correo sin receptores");
		}
		final List<CorreoDto> receptoresCC = contCorreos.getListaCorreo().stream()
				.filter(c -> TipoCorreoEnum.CC.equals(c.getTipoCorreo().getNombre()))
				.map(c -> c.getCorreo())
				.toList();
		final List<CorreoDto> receptoresCCO = contCorreos.getListaCorreo().stream()
				.filter(c -> TipoCorreoEnum.CCO.equals(c.getTipoCorreo().getNombre()))
				.map(c -> c.getCorreo())
				.toList();
		return CorreosEnvioDTO.builder()
				.idTemplate(contCorreos.getListaCorreo().stream()
						.findFirst().get().getIdTemplate())
				.servicio(servicio.get())
				.emisor(emisor.get())
				.receptores(receptores)
				.receptoresCC(receptoresCC)
				.receptoresCCO(receptoresCCO)
				.build();
	}

	private void enviarSinTemplate(final CorreosEnvioDTO envio, final MensajeDto correo, final MensajeEmailDto mensaje) {
		/*
		 * Crear mensaje
		 */
		final SimpleMailMessage emailMessage = new SimpleMailMessage();
		// FROM
		emailMessage.setFrom(envio.getEmisor().getEmail());
		// TO
		emailMessage.setTo(envio.getReceptores().stream().map(r -> r.getEmail()).toList().toArray(new String[0]));
		// CC
		if(!envio.getReceptoresCC().isEmpty()) {
			emailMessage.setCc(envio.getReceptoresCC().stream().map(r -> r.getEmail()).toList().toArray(new String[0]));
		}
		// CCO
		if(!envio.getReceptoresCCO().isEmpty()) {
			emailMessage.setBcc(envio.getReceptoresCCO().stream().map(r -> r.getEmail()).toList().toArray(new String[0]));
		}
		emailMessage.setSubject(correo.getAsunto());
		emailMessage.setText(String.format("%s\n%s", correo.getContenido(), mensaje.getMensaje()));
		// Configuracion SMTP
		final JavaMailSender mailSender = this.crearSender(envio.getServicio());
		// Enviar correo
		mailSender.send(emailMessage);
	}

	private void enviarConTemplate(final CorreosEnvioDTO envio, final MensajeDto correo, final MensajeEmailDto mensaje,
			final TemplateEntity template) {
		// Configuracion SMTP
		final JavaMailSender mailSender = this.crearSender(envio.getServicio());
		final MimeMessage emailMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(emailMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			mimeMessageHelper.setSubject(correo.getAsunto());
			mimeMessageHelper.setFrom(envio.getEmisor().getEmail());
			mimeMessageHelper.setTo(envio.getReceptores().stream().map(r -> r.getEmail()).toList().toArray(new String[0]));
			// CC
			if(!envio.getReceptoresCC().isEmpty()) {
				mimeMessageHelper.setCc(envio.getReceptoresCC().stream().map(r -> r.getEmail()).toList().toArray(new String[0]));
			}
			// CCO
			if(!envio.getReceptoresCCO().isEmpty()) {
				mimeMessageHelper.setBcc(envio.getReceptoresCCO().stream().map(r -> r.getEmail()).toList().toArray(new String[0]));
			}
			// Configurar template
			final Context contexto = new Context();
			// Variables bases
			contexto.setVariable("year", DateUtils.obtenerAgno());
			template.getVariables().stream()
				.forEach(v -> contexto.setVariable(v.getNombre(), v.getValor()));
			// Variables de entrada
			if(!ObjectUtils.isEmpty(correo.getContenido())) {
				contexto.setVariable("contenido", correo.getContenido());
			}
			contexto.setVariable("nombre_destinatario", envio.getReceptores().stream().findFirst().get().getNombre());
			// Generar HTML
			mimeMessageHelper.setText(this.templateEngine.process(template.getContenido(), contexto), true);
			// Enviar correo
			mailSender.send(emailMessage);
		} catch (MessagingException e) {
			throw new ArpisException("", e);
		}
	}

	private JavaMailSender crearSender(final CorreoDto servicio) {
		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername(servicio.getEmail());
		mailSender.setPassword(servicio.getPasword());
		mailSender.setHost(this.env.getProperty("arpis.mail.smtp.host"));
		mailSender.setPort(Integer.valueOf(this.env.getProperty("arpis.mail.smtp.port")));
		mailSender.setProtocol(this.env.getProperty("arpis.mail.transport.protocol"));
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.auth", this.env.getProperty("arpis.mail.smtp.auth"));
		props.put("mail.smtp.starttls.enable", this.env.getProperty("arpis.mail.smtp.starttls.enable"));
		props.put("mail.smtp.ssl.protocols", this.env.getProperty("arpis.mail.smtp.ssl.protocols"));
		props.put("mail.debug", this.env.getProperty("arpis.mail.debug"));
		props.put("mail.encoding", this.env.getProperty("arpis.mail.encoding"));
		return mailSender;
	}

}

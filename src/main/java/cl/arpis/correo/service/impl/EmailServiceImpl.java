package cl.arpis.correo.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

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
import cl.arpis.correo.dto.CorreosEnvioDTO;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.MensajeEmailDto;
import cl.arpis.correo.dto.datos.CorreoDto;
import cl.arpis.correo.enums.EstadoRegistroEnum;
import cl.arpis.correo.enums.TipoCorreoEnum;
import cl.arpis.correo.estructuras.RegistroCorreo;
import cl.arpis.correo.exceptions.ArpisException;
import cl.arpis.correo.exceptions.CorreoException;
import cl.arpis.correo.persistence.clientes.samsonite.entities.TemplateEntity;
import cl.arpis.correo.persistence.clientes.samsonite.repositories.TemplateRepository;
import cl.arpis.correo.persistence.general.custom.CorreosRepository;
import cl.arpis.correo.service.EmailService;
import cl.arpis.correo.util.DateUtils;
import cl.arpis.correo.util.JsonUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

	private Environment env;
	private TemplateRepository templateRepository;
	private ITemplateEngine templateEngine;
	private CorreosRepository correoRepository;

	public EmailServiceImpl(Environment env,
			TemplateRepository templateRepository,
			ITemplateEngine templateEngine,
			CorreosRepository correoRepository) {
		this.env = env;
		this.templateRepository = templateRepository;
		this.templateEngine = templateEngine;
		this.correoRepository = correoRepository;
	}

	@Override
	public void enviarResultados(final MensajeDto correo, final ContenedorCorreoDto contCorreo) {
		this.enviarEmail(correo, MensajeEmailDto.builder().mensaje("").build(), contCorreo);
	}

	@Override
	public void enviarEmail(final MensajeDto correo, final MensajeEmailDto mensaje,
			final ContenedorCorreoDto contCorreos) {
		// Obtener casillas
		final CorreosEnvioDTO envio = this.clasificarCorreos(correo, contCorreos, mensaje);
		log.info("Config correo a enviar: {}", JsonUtils.objectToJsonString(envio));
		// Para algunos casos, se registra el envio del correo
		final Optional<RegistroCorreo> registro = this.registrarCorreo(correo, envio, contCorreos); 
		if(!ObjectUtils.isEmpty(correo.getContieneTemplate()) && correo.getContieneTemplate()) {
			this.enviarConTemplate(envio, correo, mensaje, registro);
		} else if(!ObjectUtils.isEmpty(envio.getIdTemplate())) {
			// Obtener template
			final Optional<TemplateEntity> template = this.templateRepository.findById(envio.getIdTemplate());
			this.enviarConTemplate(envio, correo, mensaje, template.get(), registro);
		} else {
			this.enviarSinTemplate(envio, correo, mensaje, registro);
		}
	}

	private CorreosEnvioDTO clasificarCorreos(final MensajeDto correo, final ContenedorCorreoDto contCorreos,
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
				.filter(c -> correo.getStoreNumbers().contains(c.getNumeroTienda()))
				.peek(c -> c.getCorreo().setTipoReceptorDescripcion(ObjectUtils.isEmpty(c.getTipoReceptor())
						? null : c.getTipoReceptor().getDescripcion()))
				.map(c -> c.getCorreo()) // Obtener datos correos
				.collect(Collectors.groupingBy(CorreoDto::getEmail)) // Agruparlos por email
				.entrySet().stream() // Obtener agrupaciones
				.map(es -> es.getValue().stream().findFirst().get()) // Sacar el primer correo disponible
				.toList();
		if(receptores.isEmpty()) {
			log.error(String.format("Correo sin receptores: Mensaje %s", mensaje.toString()));
			throw new CorreoException("Correo sin receptores");
		}
		final List<CorreoDto> receptoresCC = contCorreos.getListaCorreo().stream()
				.filter(c -> TipoCorreoEnum.CC.equals(c.getTipoCorreo().getNombre()))
				.filter(c -> correo.getStoreNumbers().contains(c.getNumeroTienda()))
				.map(c -> c.getCorreo()) // Obtener datos correos
				.collect(Collectors.groupingBy(CorreoDto::getEmail)) // Agruparlos por email
				.entrySet().stream() // Obtener agrupaciones
				.map(es -> es.getValue().stream().findFirst().get()) // Sacar el primer correo disponible
				.toList();
		final List<CorreoDto> receptoresCCO = contCorreos.getListaCorreo().stream()
				.filter(c -> TipoCorreoEnum.CCO.equals(c.getTipoCorreo().getNombre()))
				.filter(c -> correo.getStoreNumbers().contains(c.getNumeroTienda()))
				.map(c -> c.getCorreo()) // Obtener datos correos
				.collect(Collectors.groupingBy(CorreoDto::getEmail)) // Agruparlos por email
				.entrySet().stream() // Obtener agrupaciones
				.map(es -> es.getValue().stream().findFirst().get()) // Sacar el primer correo disponible
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

	private void enviarSinTemplate(final CorreosEnvioDTO envio, final MensajeDto correo, final MensajeEmailDto mensaje,
			final Optional<RegistroCorreo> registro) {
		log.info("Enviando sin template: Asunto \"{}\"", correo.getAsunto());
		try {
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
		} catch (Throwable e) {
			if(registro.isPresent()) {
				registro.get().setEstado(EstadoRegistroEnum.ERROR);
				registro.get().setDetalle(e.getMessage());
				this.registrarCorreo(registro.get());
			}
			throw new ArpisException("", e);
		}
		if(registro.isPresent()) {
			registro.get().setEstado(EstadoRegistroEnum.OK);
			this.registrarCorreo(registro.get());
		}
	}

	private void enviarConTemplate(final CorreosEnvioDTO envio, final MensajeDto correo, final MensajeEmailDto mensaje,
			final TemplateEntity template, final Optional<RegistroCorreo> registro) {
		log.info("Enviando con template: Asunto \"{}\"", correo.getAsunto());
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
			if(ObjectUtils.isEmpty(envio.getReceptores().stream().findFirst().get().getTipoReceptorDescripcion())) {
				contexto.setVariable("nombre_destinatario", envio.getReceptores().stream().findFirst().get().getNombre());
			} else {
				contexto.setVariable("nombre_destinatario", envio.getReceptores().stream().findFirst().get().getTipoReceptorDescripcion());
			}
			// Generar HTML
			mimeMessageHelper.setText(this.templateEngine.process(template.getContenido(), contexto), true);
			// Enviar correo
			mailSender.send(emailMessage);
		} catch (Throwable e) {
			if(registro.isPresent()) {
				registro.get().setEstado(EstadoRegistroEnum.ERROR);
				registro.get().setDetalle(e.getMessage());
				this.registrarCorreo(registro.get());
			}
			throw new ArpisException("", e);
		}
		if(registro.isPresent()) {
			registro.get().setEstado(EstadoRegistroEnum.OK);
			this.registrarCorreo(registro.get());
		}
	}

	private void enviarConTemplate(final CorreosEnvioDTO envio, final MensajeDto correo, final MensajeEmailDto mensaje,
			final Optional<RegistroCorreo> registro) {
		log.info("Enviando con template desde entrada: Asunto \"{}\"", correo.getAsunto());
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
			// Generar HTML
			mimeMessageHelper.setText(correo.getContenido(), true);
			// Enviar correo
			mailSender.send(emailMessage);
		} catch (Throwable e) {
			if(registro.isPresent()) {
				registro.get().setEstado(EstadoRegistroEnum.ERROR);
				registro.get().setDetalle(e.getMessage());
				this.registrarCorreo(registro.get());
			}
			throw new ArpisException("", e);
		}
		if(registro.isPresent()) {
			registro.get().setEstado(EstadoRegistroEnum.OK);
			this.registrarCorreo(registro.get());
		}
	}

	private JavaMailSender crearSender(final CorreoDto servicio) {
		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername(servicio.getEmail());
		mailSender.setPassword(servicio.getPassword());
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

	private Optional<RegistroCorreo> registrarCorreo(final MensajeDto correo, final CorreosEnvioDTO envio,
			final ContenedorCorreoDto contCorreos) {
		if(1 > contCorreos.getListaCorreo().stream()
			.filter(c -> "Diferencias".equals(c.getEtapa().getNombre()))
			.count()) {
			return Optional.empty();
		}
		return Optional.of(this.correoRepository.registrarEventoCorreo(RegistroCorreo.builder()
				.asunto(ObjectUtils.isEmpty(correo.getAsunto()) ? "" : correo.getAsunto())
				.destinatarios(envio.getReceptores().stream().map(r -> r.getEmail()).toList().toString())
				.conCopia(envio.getReceptoresCC().stream().map(r -> r.getEmail()).toList().toString())
				.contenido(correo.getContenido())
				.estado(EstadoRegistroEnum.EN_PROCESO)
				.build()));
	}

	private RegistroCorreo registrarCorreo(final RegistroCorreo registro) {
		return this.correoRepository.registrarEventoCorreo(registro);
	}

}

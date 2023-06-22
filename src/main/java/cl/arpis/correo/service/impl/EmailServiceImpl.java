package cl.arpis.correo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.MensajeEmailDto;
import cl.arpis.correo.dto.datos.CorreoDto;
import cl.arpis.correo.enums.TipoCorreoEnum;
import cl.arpis.correo.exceptions.CorreoException;
import cl.arpis.correo.service.EmailService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

	private Environment env;

	public EmailServiceImpl(Environment env) {
		this.env = env;
	}

	@Override
	public void enviarResultados(final MensajeDto correo, final ContenedorCorreoDto contCorreo) {
		this.enviarEmail(correo, MensajeEmailDto.builder().mensaje("").build(), contCorreo);
//		final StringBuilder sb = new StringBuilder();
//		for(CasosDto proceso : correo.getListaCasos()) {
//			// Por cada procesamiento, concatenar mensajes para enviar un solo correo
//			sb.append(proceso.getDetalle());
//			sb.append(": ");
//			sb.append(proceso.getCaso());
//			sb.append("\n");
//		}
//		if (sb.length() > 0) {
//			this.enviarEmail(correo, MensajeEmailDto.builder().mensaje(sb.toString()).build(), contCorreo);
//		}
	}

	@Override
	public void enviarEmail(final MensajeDto correo, final MensajeEmailDto mensaje,
			final ContenedorCorreoDto contCorreos) {
		// Obtener casillas
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
		/*
		 * Crear mensaje
		 */
		final SimpleMailMessage emailMessage = new SimpleMailMessage();
		// FROM
		emailMessage.setFrom(emisor.get().getEmail());
		// TO
		emailMessage.setTo(receptores.stream().map(r -> r.getEmail()).toList().toArray(new String[0]));
		// CC
		if(!receptoresCC.isEmpty()) {
			emailMessage.setCc(receptoresCC.stream().map(r -> r.getEmail()).toList().toArray(new String[0]));
		}
		// CCO
		if(!receptoresCCO.isEmpty()) {
			emailMessage.setBcc(receptoresCCO.stream().map(r -> r.getEmail()).toList().toArray(new String[0]));
		}
		emailMessage.setSubject(correo.getAsunto());
		emailMessage.setText(String.format("%s\n%s", correo.getMensaje(), mensaje.getMensaje()));
		// Configuracion SMTP
		final JavaMailSender mailSender = this.crearSender(servicio.get());
		// Enviar correo
		mailSender.send(emailMessage);
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

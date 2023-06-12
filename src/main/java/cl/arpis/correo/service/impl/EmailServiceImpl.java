package cl.arpis.correo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import cl.arpis.correo.dto.CasosDto;
import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.CorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.MensajeEmailDto;
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
		final StringBuilder sb = new StringBuilder();
		for(CasosDto proceso : correo.getListaCasos()) {
			// Por cada procesamiento, concatenar mensajes para enviar un solo correo
			sb.append(proceso.getDetalle());
			sb.append(": ");
			sb.append(proceso.getCaso());
			sb.append("\n");
		}
		if (sb.length() > 0) {
			this.enviarEmail(correo, MensajeEmailDto.builder().mensaje(sb.toString()).build(), contCorreo);
		}
	}

	@Override
	public void enviarEmail(final MensajeDto correo, final MensajeEmailDto mensaje,
			final ContenedorCorreoDto contCorreos) {
		// Obtener casillas
		final Optional<CorreoDto> emisor = contCorreos.getListaCorreo().stream()
				.filter(a -> a.getResponsable().equals("DE"))
				.findFirst();
		if(emisor.isEmpty()) {
			log.error(String.format("Sin cuenta de servicio para conectarse al SMTP: Mensaje %s", mensaje.toString()));
			throw new CorreoException("Sin cuenta de servicio para conectarse al SMTP");
		}
		final List<CorreoDto> receptores = contCorreos.getListaCorreo().stream()
				.filter(a -> a.getResponsable().equals("PARA"))
				.collect(Collectors.toList());
		if(receptores.isEmpty()) {
			log.error(String.format("Correo sin receptores: Mensaje %s", mensaje.toString()));
			throw new CorreoException("Correo sin receptores");
		}
		final List<CorreoDto> receptoresCC = contCorreos.getListaCorreo().stream()
				.filter(a -> a.getResponsable().equals("CC"))
				.collect(Collectors.toList());
		// Configuracion SMTP
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername(emisor.get().getMail());
		mailSender.setPassword(emisor.get().getPass());
		mailSender.setHost(this.env.getProperty("arpis.mail.smtp.host"));
		mailSender.setPort(Integer.valueOf(this.env.getProperty("arpis.mail.smtp.port")));
		mailSender.setProtocol(this.env.getProperty("arpis.mail.transport.protocol"));
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.auth", this.env.getProperty("arpis.mail.smtp.auth"));
		props.put("mail.smtp.starttls.enable", this.env.getProperty("arpis.mail.smtp.starttls.enable"));
		props.put("mail.smtp.ssl.protocols", this.env.getProperty("arpis.mail.smtp.ssl.protocols"));
		props.put("mail.debug", this.env.getProperty("arpis.mail.debug"));
		props.put("mail.encoding", this.env.getProperty("arpis.mail.encoding"));
		// Crear mensaje
		final SimpleMailMessage emailMessage = new SimpleMailMessage();
		emailMessage.setFrom(emisor.get().getMail());
		final StringBuilder sb = new StringBuilder();
		receptores.stream().map(r -> r.getMail().trim().concat(",")).forEach(sb::append);
		emailMessage.setTo(sb.toString());
		sb.delete(0, sb.length());
		receptoresCC.stream().map(r -> r.getMail().trim().concat(",")).forEach(sb::append);
		emailMessage.setCc(sb.toString());
		emailMessage.setSubject(correo.getAsunto());
		emailMessage.setText(String.format("%s\n%s", correo.getMensaje(), mensaje.getMensaje()));
		// Enviar correo
		mailSender.send(emailMessage);
	}

}

package cl.arpis.correo.service.impl;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import cl.arpis.correo.dto.CasosDto;
import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.CorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.MensajeEmailDto;
import cl.arpis.correo.service.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {

	@Override
	public void enviarEmail(final MensajeDto correo, final MensajeEmailDto mensaje,
			final ContenedorCorreoDto contCorre) {
		CorreoDto emisor = new CorreoDto();
		CorreoDto receptorPrincipal = new CorreoDto();
		CorreoDto receptorCopia = new CorreoDto();
		try {
			emisor = contCorre.getListaCorreo().stream().filter(a -> a.getResponsable().equals("DE")).findFirst().get();
			receptorPrincipal = contCorre.getListaCorreo().stream().filter(a -> a.getResponsable().equals("PARA"))
					.findFirst().get();
			receptorCopia = contCorre.getListaCorreo().stream().filter(a -> a.getResponsable().equals("CC")).findFirst()
					.get();
		} catch (Exception e) {
			// TODO: handle exception
		}
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername(emisor.getMail());
		mailSender.setPassword(emisor.getPass());

		Properties props = mailSender.getJavaMailProperties();
		props.put("spring.mail.transport.protocol", "smtp");
		props.put("spring.mail.smtp.auth", "true");
		props.put("spring.mail.smtp.starttls.enable", "true");
		props.put("spring.mail.debug", "true");
		props.put("spring.mail.host", "true");
		props.put("spring.mail.port", "true");

//		contCorre.getListaCorreo().stream().filter(a -> a.getResponsable().equals("TO"));
//		contCorre.getListaCorreo().stream().filter(a -> a.getResponsable().equals("CC"));

		final SimpleMailMessage emailMessage = new SimpleMailMessage();
		emailMessage.setFrom(emisor.getMail());
		if (receptorCopia.getMail()!= null && !receptorCopia.getMail().isEmpty()) {
			emailMessage.setCc(receptorCopia.getMail());
		}
		if (receptorPrincipal.getMail()!= null &&!receptorPrincipal.getMail().isEmpty()) {
			emailMessage.setTo(receptorPrincipal.getMail());
		}
		emailMessage.setSubject(correo.getAsusto());
		emailMessage.setText(String.format("%s\n%s", correo.getMensaje(), mensaje.getMensaje()));
		mailSender.send(emailMessage);
	}

	@Override
	public void enviarResultados(final MensajeDto correo, ContenedorCorreoDto contCorre) {
		final StringBuilder sb = new StringBuilder();
		for (CasosDto proceso : correo.getListaCasos()) {
			// Por cada procesamiento, concatenar mensajes para enviar un solo correo
			sb.append(proceso.getDetalle());
			sb.append(":");
			sb.append(proceso.getCaso());
			sb.append("\n");
		}
		if (sb.length() > 0) {
			this.enviarEmail(correo, MensajeEmailDto.builder().mensaje(sb.toString()).build(), contCorre);
		}
	}
}

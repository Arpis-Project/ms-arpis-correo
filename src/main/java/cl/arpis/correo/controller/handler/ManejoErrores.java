package cl.arpis.correo.controller.handler;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.arpis.correo.dto.RespuestaErrorDTO;
import cl.arpis.correo.exceptions.CorreoException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ManejoErrores {

	/* ==================================================
	 * Errores aplicacion
	 * ================================================== */

	@ExceptionHandler(CorreoException.class)
	@ResponseBody
	private ResponseEntity<RespuestaErrorDTO> manejoError(CorreoException ex) {
		return ResponseEntity.badRequest()
				.body(RespuestaErrorDTO.builder()
						.error("Problemas procesando correo")
						.detalles(ex.getMessage())
						.build());
	}

	/* ==================================================
	 * Errores Spring
	 * ================================================== */

	@ExceptionHandler(DataAccessException.class)
	@ResponseBody
	private ResponseEntity<RespuestaErrorDTO> manejoError(DataAccessException ex) {
		log.error("", ex);
		return ResponseEntity.internalServerError()
				.body(RespuestaErrorDTO.builder()
						.error("Error interno")
						.detalles("Contacte soporte t�cnico")
						.build());
	}

	@ExceptionHandler(MailException.class)
	@ResponseBody
	private ResponseEntity<RespuestaErrorDTO> manejoError(MailException ex) {
		log.error("", ex);
		return ResponseEntity.internalServerError()
				.body(RespuestaErrorDTO.builder()
						.error("Problemas con SMTP")
						.detalles(ex.getMessage())
						.build());
	}

	@ExceptionHandler(CannotCreateTransactionException.class)
	@ResponseBody
	private ResponseEntity<RespuestaErrorDTO> manejoError(CannotCreateTransactionException ex) {
		log.error("", ex);
		return ResponseEntity.internalServerError()
				.body(RespuestaErrorDTO.builder()
						.error("Error interno")
						.detalles("Contacte soporte t�cnico")
						.build());
	}

	/* ==================================================
	 * Errores genericos
	 * ================================================== */

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	private ResponseEntity<RespuestaErrorDTO> manejoError(RuntimeException ex) {
		log.error("", ex);
		return ResponseEntity.internalServerError()
				.body(RespuestaErrorDTO.builder()
						.error("Error interno")
						.detalles("Contacte soporte t�cnico")
						.build());
	}

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	private ResponseEntity<RespuestaErrorDTO> manejoError(Throwable ex) {
		log.error("", ex);
		return ResponseEntity.internalServerError()
				.body(RespuestaErrorDTO.builder()
						.error("Error interno")
						.detalles("Contacte soporte t�cnico")
						.build());
	}

}
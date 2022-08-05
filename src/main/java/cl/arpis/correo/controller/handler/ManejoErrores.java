package cl.arpis.correo.controller.handler;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.UnexpectedTypeException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.MailException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
						.detalles(Arrays.asList(ex.getMessage()))
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
						.detalles(Arrays.asList("Contacte soporte técnico"))
						.build());
	}

	@ExceptionHandler(MailException.class)
	@ResponseBody
	private ResponseEntity<RespuestaErrorDTO> manejoError(MailException ex) {
		log.error("", ex);
		return ResponseEntity.internalServerError()
				.body(RespuestaErrorDTO.builder()
						.error("Problemas con SMTP")
						.detalles(Arrays.asList(ex.getMessage()))
						.build());
	}

	@ExceptionHandler(CannotCreateTransactionException.class)
	@ResponseBody
	private ResponseEntity<RespuestaErrorDTO> manejoError(CannotCreateTransactionException ex) {
		log.error("", ex);
		return ResponseEntity.internalServerError()
				.body(RespuestaErrorDTO.builder()
						.error("Error interno")
						.detalles(Arrays.asList("Contacte soporte técnico"))
						.build());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	private ResponseEntity<RespuestaErrorDTO> manejoError(MethodArgumentNotValidException ex) {
		return ResponseEntity.badRequest()
				.body(RespuestaErrorDTO.builder()
						.error("Error de datos")
						.detalles(ex.getFieldErrors().stream()
								.map(fe -> String.format("Campo '%s' %s", fe.getField(), fe.getDefaultMessage()))
								.collect(Collectors.toList()))
						.build());
	}	

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	private ResponseEntity<RespuestaErrorDTO> manejoError(HttpMessageNotReadableException ex) {
		return ResponseEntity.badRequest()
				.body(RespuestaErrorDTO.builder()
						.error("Error de datos")
						.detalles(Arrays.asList(ex.getMostSpecificCause().fillInStackTrace().getLocalizedMessage().split("\\n")[0]))
						.build());
	}

	/* ==================================================
	 * Errores javax validations
	 * ================================================== */

	@ExceptionHandler(UnexpectedTypeException.class)
	@ResponseBody
	private ResponseEntity<RespuestaErrorDTO> manejoError(UnexpectedTypeException ex) {
		final String[] splited = ex.getMessage().split("\\.");
		return ResponseEntity.badRequest()
				.body(RespuestaErrorDTO.builder()
						.error("Error de datos")
						.detalles(Arrays.asList(splited[splited.length - 1].trim()))
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
						.detalles(Arrays.asList("Contacte soporte técnico"))
						.build());
	}

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	private ResponseEntity<RespuestaErrorDTO> manejoError(Throwable ex) {
		log.error("", ex);
		return ResponseEntity.internalServerError()
				.body(RespuestaErrorDTO.builder()
						.error("Error interno")
						.detalles(Arrays.asList("Contacte soporte técnico"))
						.build());
	}

}

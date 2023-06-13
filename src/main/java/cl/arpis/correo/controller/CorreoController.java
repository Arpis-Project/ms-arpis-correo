package cl.arpis.correo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.RespuestaDto;
import cl.arpis.correo.service.CorreoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/correo")
public class CorreoController {

	private CorreoService serviceCorreo;

	public CorreoController(CorreoService serviceCorreo) {
		this.serviceCorreo = serviceCorreo;
	}

	@PostMapping(path = "/envio",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaDto> enviarCorreo(
			@RequestBody @Valid MensajeDto correo,
			@RequestHeader(name = "Authorization", required = true) String usuario) {
		RespuestaDto resp = this.serviceCorreo.enviarCorreo(correo);
		return ResponseEntity.ok(resp);
	}

	@GetMapping(path = "/proyecto/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ContenedorCorreoDto> listarCorreos(
			@PathVariable(name = "id", required = true) Integer idProyecto) {
		return ResponseEntity.ok(this.serviceCorreo.buscarCorreos(idProyecto));
	}

	@GetMapping(path = "/proyecto/{id}/error/{error}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ContenedorCorreoDto> listarCorreos(
			@PathVariable(name = "id", required = true) Integer idProyecto,
			@PathVariable(name = "error", required = true) Short idTipoError) {
		return ResponseEntity.ok(this.serviceCorreo.buscarCorreos(idProyecto, idTipoError));
	}

}

package cl.arpis.correo.controllers;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.datos.TemplateDTO;
import cl.arpis.correo.service.CorreoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/correos")
public class CorreoController {

	private CorreoService correoService;

	public CorreoController(CorreoService correoService) {
		this.correoService = correoService;
	}

	@PostMapping(path = "/envio",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> enviarCorreo(
			@RequestBody(required = true) @Valid MensajeDto correo) {
		this.correoService.enviarCorreo(correo);
		return ResponseEntity.created(null).build();
	}

	@GetMapping(path = "/proyectos/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ContenedorCorreoDto> listarCorreos(
			@PathVariable(name = "id", required = true) Integer idProyecto) {
		return ResponseEntity.ok(this.correoService.buscarCorreos(idProyecto));
	}

	@GetMapping(path = "/proyectos/{id_proyecto}/etapas/{id_etapa}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ContenedorCorreoDto> listarCorreos(
			@PathVariable(name = "id_proyecto", required = true) Integer idProyecto,
			@PathVariable(name = "id_etapa", required = true) Long idEtapa) {
		return ResponseEntity.ok(this.correoService.buscarCorreos(idProyecto, idEtapa));
	}

	@Deprecated
	@GetMapping(path = "/proyectos/{id}/error/{error}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ContenedorCorreoDto> listarCorreos(
			@PathVariable(name = "id", required = true) Integer idProyecto,
			@PathVariable(name = "error", required = true) Short idTipoError) {
		return ResponseEntity.ok(this.correoService.buscarCorreos(idProyecto, idTipoError));
	}

	@GetMapping(path = "/templates/{id_template}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TemplateDTO> obtenerTemplate(
			@PathVariable(name = "id_template", required = true) Integer idTemplate) {
		final Optional<TemplateDTO> template = this.correoService.obtenerTemplate(idTemplate);
		if(template.isPresent()) {
			return ResponseEntity.ok(template.get());
		}
		return ResponseEntity.noContent().build();
	}

}

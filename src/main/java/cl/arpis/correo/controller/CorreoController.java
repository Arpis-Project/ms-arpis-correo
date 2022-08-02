package cl.arpis.correo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.arpis.correo.dto.EnvioCorreoDto;
import cl.arpis.correo.dto.RespuestaDto;
import cl.arpis.correo.service.ICorreoService;

@RestController
@RequestMapping("/correo")
public class CorreoController {
	@Autowired
	private ICorreoService serviceCorreo;
	
	public CorreoController(ICorreoService serviceCorreo) {
		this.serviceCorreo= serviceCorreo;
	}

	@PostMapping(path = "/envio", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaDto> enviarCorreo(@RequestBody EnvioCorreoDto correo,
			@RequestHeader(name = "Authorization", required = true) String usuario) {
		RespuestaDto resp = serviceCorreo.enviarCorreo(correo);
		return ResponseEntity.ok(resp);
	}
}

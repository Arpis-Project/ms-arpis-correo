package cl.arpis.correo.service.impl;

import org.springframework.stereotype.Service;

import cl.arpis.correo.dto.EnvioCorreoDto;
import cl.arpis.correo.dto.RespuestaDto;
import cl.arpis.correo.service.ICorreoService;

@Service
public class CorreoService implements ICorreoService {

	@Override
	public RespuestaDto enviarCorreo(EnvioCorreoDto correo) {
		return null;
	}
}

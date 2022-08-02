package cl.arpis.correo.service;

import cl.arpis.correo.dto.EnvioCorreoDto;
import cl.arpis.correo.dto.RespuestaDto;

public interface ICorreoService {

	public RespuestaDto enviarCorreo(EnvioCorreoDto correo);
}

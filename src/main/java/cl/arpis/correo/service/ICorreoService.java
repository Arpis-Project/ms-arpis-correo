package cl.arpis.correo.service;

import java.util.List;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.CorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.RespuestaDto;

public interface ICorreoService {

	public RespuestaDto enviarCorreo(MensajeDto correo);

	public ContenedorCorreoDto buscarCorreos(long idPoryecto, String error);

	public ContenedorCorreoDto buscarCorreos(long idPoryecto);

}

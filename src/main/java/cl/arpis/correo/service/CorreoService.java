package cl.arpis.correo.service;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.RespuestaDto;

/**
 * 
 * @author steph
 *
 */
public interface CorreoService {

	public RespuestaDto enviarCorreo(MensajeDto correo);

	public ContenedorCorreoDto buscarCorreos(Integer idProyecto);

	public ContenedorCorreoDto buscarCorreos(Integer idProyecto, Short idTipoError);

}

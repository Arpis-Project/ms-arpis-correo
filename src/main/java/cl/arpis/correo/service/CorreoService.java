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

	RespuestaDto enviarCorreo(MensajeDto correo);

	ContenedorCorreoDto buscarCorreos(Integer idProyecto);

	ContenedorCorreoDto buscarCorreos(Integer idProyecto, Long idEtapa);

	ContenedorCorreoDto buscarCorreos(Integer idProyecto, Short idTipoError);

}

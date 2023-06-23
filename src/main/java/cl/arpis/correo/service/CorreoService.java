package cl.arpis.correo.service;

import java.util.Optional;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.RespuestaDto;
import cl.arpis.correo.dto.datos.TemplateDTO;

/**
 * 
 * @author steph
 *
 */
public interface CorreoService {

	RespuestaDto enviarCorreo(MensajeDto correo);

	ContenedorCorreoDto buscarCorreos(Integer idProyecto);

	ContenedorCorreoDto buscarCorreos(Integer idProyecto, Long idEtapa);

	Optional<TemplateDTO> obtenerTemplate(Integer idTemplate);

	@Deprecated
	ContenedorCorreoDto buscarCorreos(Integer idProyecto, Short idTipoError);

}

package cl.arpis.correo.persistence.general.custom;

import java.util.List;
import java.util.Optional;

import cl.arpis.correo.dto.CorreoDto;
import cl.arpis.correo.dto.UsuariosDto;

/**
 * 
 * @author steph
 * @param <T>
 *
 */
public interface CustomCorreosRepository {

	List<CorreoDto> buscarCorreos(Long idProyecto);

	List<CorreoDto> buscarCorreos(Long idProyecto, String error);

	Optional<UsuariosDto> buscarUsuario(String usuario);

}

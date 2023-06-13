package cl.arpis.correo.persistence.general.custom;

import java.util.List;
import java.util.Optional;

import cl.arpis.correo.dto.CorreoDto;
import cl.arpis.correo.dto.UsuarioDto;

/**
 * 
 * @author steph
 * @param <T>
 *
 */
public interface CorreosRepository {

	List<CorreoDto> buscarCorreos(Long idProyecto);

	List<CorreoDto> buscarCorreos(Long idProyecto, String error);

	Optional<UsuarioDto> buscarUsuario(String usuario);

}

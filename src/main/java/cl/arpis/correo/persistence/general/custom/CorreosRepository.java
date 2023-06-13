package cl.arpis.correo.persistence.general.custom;

import java.util.List;
import java.util.Optional;

import cl.arpis.correo.dto.datos.ProyectoCorreoDto;
import cl.arpis.correo.dto.datos.ProyectoErrorDto;
import cl.arpis.correo.dto.datos.UsuarioDto;

/**
 * 
 * @author steph
 * @param <T>
 *
 */
public interface CorreosRepository {

	Optional<UsuarioDto> buscarUsuario(String usuario);

	List<ProyectoCorreoDto> obtenerProyectoCorreo(Integer idProyecto);
	
	List<ProyectoErrorDto> obtenerProyectoErrorPorTipo(Integer idProyecto, Short idTipoError);

}

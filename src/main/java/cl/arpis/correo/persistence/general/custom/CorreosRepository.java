package cl.arpis.correo.persistence.general.custom;

import java.util.List;
import java.util.Optional;

import cl.arpis.correo.dto.datos.ProyectoCorreoDto;
import cl.arpis.correo.dto.datos.ProyectoErrorDto;
import cl.arpis.correo.dto.datos.UsuarioDto;
import cl.arpis.correo.estructuras.RegistroCorreo;

/**
 * 
 * @author steph
 * @param <T>
 *
 */
public interface CorreosRepository {

	Optional<UsuarioDto> buscarUsuario(String usuario);

	List<ProyectoCorreoDto> obtenerProyectoCorreo(Integer idProyecto);

	List<ProyectoCorreoDto> obtenerProyectoCorreo(Integer idProyecto, Long idEtapa);

	RegistroCorreo registrarEventoCorreo(RegistroCorreo registro);

	@Deprecated
	List<ProyectoErrorDto> obtenerProyectoErrorPorTipo(Integer idProyecto, Short idTipoError);

}

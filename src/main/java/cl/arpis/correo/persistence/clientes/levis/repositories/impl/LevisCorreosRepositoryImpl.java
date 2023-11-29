package cl.arpis.correo.persistence.clientes.levis.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.arpis.correo.dto.datos.ProyectoCorreoDto;
import cl.arpis.correo.dto.datos.ProyectoErrorDto;
import cl.arpis.correo.dto.datos.UsuarioDto;
import cl.arpis.correo.estructuras.RegistroCorreo;
import cl.arpis.correo.persistence.general.custom.CorreosRepository;

/**
 * 
 * @author steph
 *
 */
@Repository
@Transactional
@Profile("levis")
public class LevisCorreosRepositoryImpl implements CorreosRepository {

	@Override
	public Optional<UsuarioDto> buscarUsuario(String usuario) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<ProyectoCorreoDto> obtenerProyectoCorreo(Integer idProyecto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProyectoCorreoDto> obtenerProyectoCorreo(Integer idProyecto, Long idEtapa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProyectoErrorDto> obtenerProyectoErrorPorTipo(Integer idProyecto, Short idTipoError) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistroCorreo registrarEventoCorreo(RegistroCorreo registro) {
		// TODO Auto-generated method stub
		return null;
	}

}

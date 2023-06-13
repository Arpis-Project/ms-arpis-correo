package cl.arpis.correo.persistence.clientes.samsonite.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.arpis.correo.dto.datos.ProyectoCorreoDto;
import cl.arpis.correo.dto.datos.ProyectoErrorDto;
import cl.arpis.correo.dto.datos.UsuarioDto;
import cl.arpis.correo.persistence.clientes.samsonite.entities.ProyectoCorreoEntity;
import cl.arpis.correo.persistence.clientes.samsonite.entities.ProyectoErrorEntity;
import cl.arpis.correo.persistence.clientes.samsonite.entities.UsuarioEntity;
import cl.arpis.correo.persistence.clientes.samsonite.repositories.ProyectoCorreoRepository;
import cl.arpis.correo.persistence.clientes.samsonite.repositories.ProyectoErrorRepository;
import cl.arpis.correo.persistence.clientes.samsonite.repositories.UsuarioRepository;
import cl.arpis.correo.persistence.general.custom.CorreosRepository;
import cl.arpis.correo.util.MapperUtils;

/**
 * 
 * @author steph
 *
 */
@Repository
@Transactional
@Profile("samsonite")
public class SamsoniteCorreosRepositoryImpl implements CorreosRepository {

	private UsuarioRepository usuariosRepository;
	private ProyectoCorreoRepository proyectoCorreoRepository;
	private ProyectoErrorRepository proyectoErrorRepository;

	public SamsoniteCorreosRepositoryImpl(UsuarioRepository usuariosRepository,
			ProyectoCorreoRepository proyectoCorreoRepository,
			ProyectoErrorRepository proyectoErrorRepository) {
		this.usuariosRepository = usuariosRepository;
		this.proyectoCorreoRepository = proyectoCorreoRepository;
		this.proyectoErrorRepository = proyectoErrorRepository;
	}

	@Override
	public Optional<UsuarioDto> buscarUsuario(final String usuario) {
		Optional<UsuarioEntity> usuarioDb = this.usuariosRepository.findByLogin(usuario);
		if (usuarioDb.isPresent()) {
			return Optional.of(MapperUtils.objectToObject(usuarioDb.get(), UsuarioDto.class));
		}
		return Optional.empty();
	}

	@Override
	public List<ProyectoCorreoDto> obtenerProyectoCorreo(final Integer idProyecto) {
		final List<ProyectoCorreoEntity> proyectoInfo = this.proyectoCorreoRepository.findByProyecto_Id(idProyecto);
		if(proyectoInfo.isEmpty()) {
			return new ArrayList<>();
		}
		return MapperUtils.listToList(proyectoInfo, ProyectoCorreoDto.class);
	}

	@Override
	public List<ProyectoErrorDto> obtenerProyectoErrorPorTipo(final Integer idProyecto, final Short idTipoError) {
		final List<ProyectoErrorEntity> proyectoInfo = this.proyectoErrorRepository.findByProyecto_IdAndTipoError_Id(idProyecto, idTipoError);
		if(proyectoInfo.isEmpty()) {
			return new ArrayList<>();
		}
		return MapperUtils.listToList(proyectoInfo, ProyectoErrorDto.class);
	}

}

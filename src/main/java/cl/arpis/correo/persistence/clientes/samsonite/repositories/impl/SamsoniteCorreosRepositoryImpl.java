package cl.arpis.correo.persistence.clientes.samsonite.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.arpis.correo.dto.CorreoDto;
import cl.arpis.correo.dto.UsuarioDto;
import cl.arpis.correo.exceptions.CorreoDbException;
import cl.arpis.correo.persistence.clientes.samsonite.entities.CorreoEntity;
import cl.arpis.correo.persistence.clientes.samsonite.entities.UsuarioEntity;
import cl.arpis.correo.persistence.clientes.samsonite.repositories.ProyectoCorreoRepository;
import cl.arpis.correo.persistence.clientes.samsonite.repositories.ProyectoErrorRepository;
import cl.arpis.correo.persistence.clientes.samsonite.repositories.UsuarioRepository;
import cl.arpis.correo.persistence.general.custom.CorreosRepository;
import cl.arpis.correo.util.MapperUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

/**
 * 
 * @author steph
 *
 */
@Repository
@Transactional
@Profile("samsonite")
@SuppressWarnings("unchecked")
public class SamsoniteCorreosRepositoryImpl implements CorreosRepository {

	private EntityManager entityManager;
	private Environment env;
	private UsuarioRepository usuariosRepository;
	private ProyectoCorreoRepository proyectoCorreoRepository;
	private ProyectoErrorRepository proyectoErrorRepository;

	public SamsoniteCorreosRepositoryImpl(Environment env,
			EntityManager entityManager,
			UsuarioRepository usuariosRepository,
			ProyectoCorreoRepository proyectoCorreoRepository,
			ProyectoErrorRepository proyectoErrorRepository) {
		this.env = env;
		this.entityManager = entityManager;
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
	public List<CorreoDto> buscarCorreos(Long idProyecto) {
		try {
			final List<CorreoEntity> correos = (List<CorreoEntity>) this.entityManager
					.createNativeQuery(this.env.getProperty("listaCorreo"), CorreoEntity.class)
					.setParameter("ID_PROYECTO", idProyecto)
					.getResultList();
			return MapperUtils.listToList(correos, CorreoDto.class);
		} catch (NoResultException e) {
			return new ArrayList<>();
		} catch (DataAccessException e) {
			throw new CorreoDbException("", e);
		}
	}

	@Override
	public List<CorreoDto> buscarCorreos(Long idProyecto, String error) {
		try {
			final List<CorreoEntity> correos = this.entityManager
					.createNativeQuery(this.env.getProperty("listaCorreoError"), CorreoEntity.class)
					.setParameter("ID_PROYECTO", idProyecto)
					.setParameter("ID_TIPO_ERROR", error)
					.getResultList();
			return MapperUtils.listToList(correos, CorreoDto.class);
		} catch (NoResultException e) {
			return new ArrayList<>();
		} catch (DataAccessException e) {
			throw new CorreoDbException("", e);
		}
	}

}

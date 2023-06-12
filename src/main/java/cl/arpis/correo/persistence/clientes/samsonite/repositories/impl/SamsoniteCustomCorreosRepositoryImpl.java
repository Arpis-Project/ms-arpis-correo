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
import cl.arpis.correo.dto.UsuariosDto;
import cl.arpis.correo.exceptions.CorreoDbException;
import cl.arpis.correo.persistence.clientes.samsonite.entities.ApiUsuariosEntity;
import cl.arpis.correo.persistence.clientes.samsonite.entities.CorreoEntity;
import cl.arpis.correo.persistence.clientes.samsonite.repositories.ApiUsuariosRepository;
import cl.arpis.correo.persistence.general.custom.CustomCorreosRepository;
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
@SuppressWarnings("unchecked")
@Profile("samsonite")
public class SamsoniteCustomCorreosRepositoryImpl implements CustomCorreosRepository {

	private EntityManager entiManager;
	private Environment env;
	private ApiUsuariosRepository apiUsuariosRepository;

	public SamsoniteCustomCorreosRepositoryImpl(Environment env,
			EntityManager entiManager,
			ApiUsuariosRepository apiUsuariosRepository) {
		this.env = env;
		this.entiManager = entiManager;
		this.apiUsuariosRepository = apiUsuariosRepository;
	}

	@Override
	public List<CorreoDto> buscarCorreos(Long idProyecto) {
		try {
			final List<CorreoEntity> correos = this. entiManager
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
			final List<CorreoEntity> correos = this.entiManager
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

	@Override
	public Optional<UsuariosDto> buscarUsuario(final String usuario) {
		Optional<ApiUsuariosEntity> usuarioDb = this.apiUsuariosRepository.findByLogin(usuario);
		if (usuarioDb.isPresent()) {
			return Optional.of(MapperUtils.objectToObject(usuarioDb.get(), UsuariosDto.class));
		}
		return Optional.empty();
	}

}

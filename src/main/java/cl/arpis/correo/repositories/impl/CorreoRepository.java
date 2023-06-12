package cl.arpis.correo.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.arpis.correo.config.QueriesConfig;
import cl.arpis.correo.entities.CorreoEntity;
import cl.arpis.correo.repositories.ICorreoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class CorreoRepository implements ICorreoRepository {

	private EntityManager entiManager;
	private QueriesConfig query;

	public CorreoRepository(EntityManager entiManager, QueriesConfig query) {
		this.entiManager = entiManager;
		this.query = query;
	}

	@Override
	public List<CorreoEntity> buscarCorreo(long idProyecto, String error) {
		List<CorreoEntity> listCorreo = new ArrayList<>();
		try {
			listCorreo.addAll((List<CorreoEntity>) this.entiManager
					.createNativeQuery(this.query.getQueryByName("listaCorreoError"), CorreoEntity.class)
					.setParameter("ID_PROYECTO", idProyecto)
					.setParameter("ID_TIPO_ERROR", error)
					.getResultList());
		} catch (NoResultException e) {
			// Todo OK
		}
		return listCorreo;
	}

	@Override
	public List<CorreoEntity> buscarCorreo(long idProyecto) {
		List<CorreoEntity> listCorreo = new ArrayList<>();
		try {
			listCorreo.addAll((List<CorreoEntity>)this. entiManager
					.createNativeQuery(this.query.getQueryByName("listaCorreo"), CorreoEntity.class)
					.setParameter("ID_PROYECTO", idProyecto)
					.getResultList());
		} catch (NoResultException e) {
			// Todo OK
		}
		return listCorreo;
	}

}

package cl.arpis.correo.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import cl.arpis.correo.config.QueriesConfig;
import cl.arpis.correo.entities.CorreoEntity;
import cl.arpis.correo.repositories.ICorreoRepository;

@Repository
@SuppressWarnings("unchecked")
public class CorreoRepository implements ICorreoRepository {

	private EntityManager entiManager;
	private QueriesConfig query;

	public CorreoRepository(EntityManager entiManager, QueriesConfig query) {
		this.entiManager = entiManager;
		this.query = query;
	}

	@Override
	public List<CorreoEntity> buscarCorreo(int idProyecto, String error) {
		List<CorreoEntity> listCorreo = new ArrayList<>();
		try {
			listCorreo.addAll((List<CorreoEntity>) entiManager
					.createNativeQuery(query.getQueryByName("listaCorreoError"), CorreoEntity.class)
					.setParameter("ID_PROYECTO", idProyecto)
					.setParameter("ID_TIPO_ERROR", error)
					.getResultList());
		} catch (NoResultException e) {
			//handle exception
		}
		return listCorreo;
	}

	@Override
	public List<CorreoEntity> buscarCorreo(int idProyecto) {
		List<CorreoEntity> listCorreo = new ArrayList<>();
		try {
			listCorreo.addAll((List<CorreoEntity>) entiManager
					.createNativeQuery(query.getQueryByName("listaCorreo"), CorreoEntity.class)
					.setParameter("ID_PROYECTO", idProyecto)
					.getResultList());
		} catch (NoResultException e) {
			//handle exception
		}
		return listCorreo;
	}
}

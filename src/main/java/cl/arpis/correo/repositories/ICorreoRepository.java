package cl.arpis.correo.repositories;

import java.util.List;

import cl.arpis.correo.entities.CorreoEntity;

public interface ICorreoRepository {

	List<CorreoEntity> buscarCorreo(long idProyecto, String error);

	List<CorreoEntity> buscarCorreo(long idProyecto);

}

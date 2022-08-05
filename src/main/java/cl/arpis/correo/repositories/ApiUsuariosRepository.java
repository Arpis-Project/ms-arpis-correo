package cl.arpis.correo.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import cl.arpis.correo.entities.ApiUsuariosEntity;

public interface ApiUsuariosRepository extends CrudRepository<ApiUsuariosEntity, Long> {

	List<ApiUsuariosEntity> findByLogin(String login);
}

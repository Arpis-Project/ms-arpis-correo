package cl.arpis.correo.persistence.clientes.levis.repositories;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.persistence.clientes.levis.entities.ApiUsuariosEntity;

/**
 * 
 * @author steph
 *
 */
@Profile("levis")
public interface ApiUsuariosRepository extends JpaRepository<ApiUsuariosEntity, Long> {

	Optional<ApiUsuariosEntity> findByLogin(String login);

}

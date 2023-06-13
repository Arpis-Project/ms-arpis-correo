package cl.arpis.correo.persistence.clientes.levis.repositories;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.persistence.clientes.levis.entities.UsuarioEntity;

/**
 * 
 * @author steph
 *
 */
@Profile("levis")
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	Optional<UsuarioEntity> findByLogin(String login);

}

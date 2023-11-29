package cl.arpis.correo.persistence.clientes.samsonite.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.persistence.clientes.samsonite.entities.RegistroEntity;

/**
 * 
 * @author arpis
 *
 */
@Profile("samsonite")
public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {

}

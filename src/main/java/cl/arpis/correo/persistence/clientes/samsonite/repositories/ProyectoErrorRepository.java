package cl.arpis.correo.persistence.clientes.samsonite.repositories;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.persistence.clientes.samsonite.entities.ProyectoErrorEntity;

/**
 * 
 * @author steph
 *
 */
@Profile("samsonite")
@Deprecated
public interface ProyectoErrorRepository extends JpaRepository<ProyectoErrorEntity, Long> {

	List<ProyectoErrorEntity> findByProyecto_IdAndTipoError_Id(Integer idProyecto, Short idTipoError);

}

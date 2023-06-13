package cl.arpis.correo.persistence.clientes.fuenzalida.repositories;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.persistence.clientes.fuenzalida.entities.ProyectoCorreoEntity;

/**
 * 
 * @author steph
 *
 */
@Profile("fuenzalida")
public interface ProyectoCorreoRepository extends JpaRepository<ProyectoCorreoEntity, Long>  {

	List<ProyectoCorreoEntity> findByProyecto_Id(Integer idProyecto);

}

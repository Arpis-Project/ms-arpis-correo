package cl.arpis.correo.persistence.clientes.samsonite.repositories;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.persistence.clientes.samsonite.entities.ProyectoCorreoEntity;

/**
 * 
 * @author steph
 *
 */
@Profile("samsonite")
public interface ProyectoCorreoRepository extends JpaRepository<ProyectoCorreoEntity, Long>  {

	List<ProyectoCorreoEntity> findByEtapa_Proyecto_Id(Integer idProyecto);

	List<ProyectoCorreoEntity> findByEtapa_Proyecto_IdAndEtapa_Id(Integer idProyecto, Long idEtapa);

}

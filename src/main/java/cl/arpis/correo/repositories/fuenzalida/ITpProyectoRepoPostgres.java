package cl.arpis.correo.repositories.fuenzalida;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.entities.TpProyectoEntity;

public interface ITpProyectoRepoPostgres extends JpaRepository<TpProyectoEntity, Long>{

}

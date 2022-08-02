package cl.arpis.correo.repositories.fuenzalida;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.entities.ProyectoErrorEntity;

public interface IProyectoErrorRepoPostgres extends JpaRepository<ProyectoErrorEntity, Long>{

}

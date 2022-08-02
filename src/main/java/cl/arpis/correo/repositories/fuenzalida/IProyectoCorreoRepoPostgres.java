package cl.arpis.correo.repositories.fuenzalida;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.entities.ProyectoCorreoEntity;

public interface IProyectoCorreoRepoPostgres extends JpaRepository<ProyectoCorreoEntity, Long>{

}

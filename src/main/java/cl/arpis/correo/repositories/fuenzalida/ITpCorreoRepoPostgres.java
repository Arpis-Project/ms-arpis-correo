package cl.arpis.correo.repositories.fuenzalida;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.entities.TpCorreoEntity;

public interface ITpCorreoRepoPostgres  extends JpaRepository<TpCorreoEntity, Long>{

}

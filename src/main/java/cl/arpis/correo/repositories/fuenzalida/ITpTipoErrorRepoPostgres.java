package cl.arpis.correo.repositories.fuenzalida;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.entities.TpTipoErrorEntity;

public interface ITpTipoErrorRepoPostgres extends JpaRepository<TpTipoErrorEntity, Long>{

}

package cl.arpis.correo.repositories.fuenzalida;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.entities.TpTipoEnvioEntity;

public interface ITpTipoEnvioRepoPostgres extends JpaRepository<TpTipoEnvioEntity, Long>{

}

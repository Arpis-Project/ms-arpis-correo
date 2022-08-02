package cl.arpis.correo.repositories.fuenzalida;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.arpis.correo.entities.TpTipoCorreoEntity;

public interface ITpTipoCorreoRepoPostgres extends JpaRepository<TpTipoCorreoEntity, Long>{

}

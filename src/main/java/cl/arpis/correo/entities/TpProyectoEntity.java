package cl.arpis.correo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "API_CC_TP_PROYECTO")
@Builder
public class TpProyectoEntity{

	@Id
	@Column(name = "ID_PROYECTO", nullable = false)
	private Long idProyecto;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Column(name = "ACTIVO", nullable = false)
	private String activo;

}

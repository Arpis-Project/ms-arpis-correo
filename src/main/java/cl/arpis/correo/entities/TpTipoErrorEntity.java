package cl.arpis.correo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "API_CC_PROYECTO_CORREO")
@Builder
public class TpTipoErrorEntity {

	@Id
	@Column(name = "ID_TIPO_ERROR", nullable = false)
	private Long idTipoError;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Column(name = "ACTIVO", nullable = false)
	private String activo;

}

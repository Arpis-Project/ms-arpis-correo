package cl.arpis.correo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "API_CC_TP_PROYECTO")
@Builder
public class TpProyectoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3996154279298930768L;

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

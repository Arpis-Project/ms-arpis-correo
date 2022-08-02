package cl.arpis.correo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "API_CC_PROYECTO_CORREO")
@Builder
public class TpTipoErrorEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4404336116544658333L;

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

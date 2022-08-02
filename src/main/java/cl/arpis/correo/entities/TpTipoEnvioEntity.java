package cl.arpis.correo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "API_CC_TP_TIPO_ENVIO")
@Builder
public class TpTipoEnvioEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2869288585693399437L;

	@Id
	@Column(name = "ID_TIPO_ENVIO", nullable = false)
	private Long idTipoEnvio;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Column(name = "ACTIVO", nullable = false)
	private String activo;
}

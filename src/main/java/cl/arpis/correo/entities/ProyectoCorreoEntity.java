package cl.arpis.correo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "API_CC_PROYECTO_CORREO")
@Builder
public class ProyectoCorreoEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7418005602912583816L;

	@Id
	@Column(name = "ID_PROYECTOCORREO", nullable = false)
	private Long idProyectoCorreo;

	@Column(name = "ID_PROYECTO", nullable = false)
	private Long idProyecto;

	@Column(name = "ID_CORREO", nullable = false)
	private Long idCorreo;

	@Column(name = "ID_TIPO_ENVIO", nullable = false)
	private Long idTipoEnvio;
	
	@Column(name = "FECHA_REACION", nullable = false)
	private Date fechaCreacion;
}

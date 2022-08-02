package cl.arpis.correo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "API_CC_TP_CORREOS")
@Builder
public class TpCorreoEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 3349705957339345629L;
	@Id
	@Column(name = "ID_CORREO", nullable = false)
	private Long idCorreo;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "ID_TIPO_CORREO", nullable = false)
	private Long idTipoCorreo;

	@Column(name = "PASS", nullable = false)
	private String pasword;
	
	@Column(name = "ACTIVO", nullable = false)
	private String activo;
}

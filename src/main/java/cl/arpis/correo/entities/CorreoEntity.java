package cl.arpis.correo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
@Entity
@Builder
public class CorreoEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8393737179434037349L;
	@Id
	@Column(name = "nombre", nullable = false)
	private String responsable;
	@Id
	@Column(name = "email", nullable = false)
	private String mail ;
	
	@Column(name = "pass")
	private String pass;
}

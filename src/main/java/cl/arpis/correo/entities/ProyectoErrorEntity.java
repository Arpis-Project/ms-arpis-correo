package cl.arpis.correo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "API_CC_TIPO_ERROR")
@Builder
public class ProyectoErrorEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1874202456011541502L;

	@Id
	@Column(name = "ID_PROYECTOCORREO", nullable = false)
	private Long idProyectoCorreo;

	@Column(name = "ID_TIPO_ERROR", nullable = false)
	private Long idTipoError;
}

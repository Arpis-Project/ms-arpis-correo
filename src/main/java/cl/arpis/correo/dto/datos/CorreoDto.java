package cl.arpis.correo.dto.datos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import cl.arpis.correo.enums.ActivoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author steph
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(Include.NON_NULL)
public class CorreoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4114610840856447460L;

	private Integer id;
	private String email;
	private String nombre;
	@JsonIgnore
	private String pasword;
	private ActivoEnum activo;
	@JsonIgnore
	private ProyectoDto proyecto;

}

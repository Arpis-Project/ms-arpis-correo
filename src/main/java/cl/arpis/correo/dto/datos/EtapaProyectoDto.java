package cl.arpis.correo.dto.datos;

import java.io.Serializable;

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
public class EtapaProyectoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -443845126960419237L;

	private Long id;
	private String nombre;
	private String descripcion;
	private ActivoEnum activo;
	private ProyectoDto proyecto;

}

package cl.arpis.correo.dto.datos;

import java.io.Serializable;
import java.util.List;

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
public class TemplateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2708838354041064348L;

	private Integer id;
	private String contenido;
	private ActivoEnum activo;
	private List<TemplateVariablesDTO> variables;

}

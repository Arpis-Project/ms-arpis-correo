package cl.arpis.correo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import cl.arpis.correo.dto.datos.CorreoDto;
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
public class CorreosEnvioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9132718778054898549L;

	private Integer idTemplate;
	private CorreoDto servicio;
	private CorreoDto emisor;
	private List<CorreoDto> receptores;
	private List<CorreoDto> receptoresCC;
	private List<CorreoDto> receptoresCCO;

}

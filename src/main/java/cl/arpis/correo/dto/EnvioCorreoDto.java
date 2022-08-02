package cl.arpis.correo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EnvioCorreoDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6955170255384965705L;
	@JsonProperty(value = "proyecto")
	private Long proyecto;
	@JsonProperty(value = "codigo_error")
	private String codError;
	@JsonProperty(value = "asusto")
	private String asusto;
	@JsonProperty(value = "mensaje")
	private String mensaje;
	@JsonProperty(value = "lista_casos")
	private List<CasosDto> listaCasos;
}

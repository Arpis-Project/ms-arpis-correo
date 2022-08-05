package cl.arpis.correo.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class MensajeDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6955170255384965705L;
	@NotNull
	private Long proyecto;
	private String codError;
	@NotNull
	@NotEmpty
	private String asunto;
	@NotNull
	@NotEmpty
	private String mensaje;
	@NotNull
	@NotEmpty
	private List<CasosDto> listaCasos;

}

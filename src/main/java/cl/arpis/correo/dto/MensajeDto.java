package cl.arpis.correo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(Include.NON_NULL)
public class MensajeDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6955170255384965705L;
	@NotNull
	private Integer proyecto;
	@NotNull
	private Long idEtapa;
	@NotEmpty
	private String asunto;
	@NotEmpty
	private String mensaje;
	private Short codError;
	private List<CasosDto> listaCasos;

}

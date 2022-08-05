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
public class ContenedorCorreoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2993733803645325855L;

	@JsonProperty(value = "lista_correo")
	private List<CorreoDto> listaCorreo;

}

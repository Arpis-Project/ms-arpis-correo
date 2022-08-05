package cl.arpis.correo.dto;

import java.io.Serializable;

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
public class CorreoDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4114610840856447460L;
	@JsonProperty(value = "tipo_envio")
	private String responsable;
	
	@JsonProperty(value = "email")
	private String mail;
	
	@JsonProperty(value = "pass")
	private String pass;
}

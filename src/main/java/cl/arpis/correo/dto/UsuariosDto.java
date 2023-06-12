package cl.arpis.correo.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author David
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(Include.NON_NULL)
public class UsuariosDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6542305039310125419L;

	private Long idUsuarioApi;
	private String login;
	private String password;
	private String nombreEmpresa;
	private String activo;
	private Date fechaCreacion;
	private String urlIntegracion;

}

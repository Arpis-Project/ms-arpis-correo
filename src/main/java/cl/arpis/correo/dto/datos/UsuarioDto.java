package cl.arpis.correo.dto.datos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @author David
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(Include.NON_NULL)
public class UsuarioDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6542305039310125419L;

	private Short id;
	private String login;
	@JsonIgnore
	private String password;
	private String nombreEmpresa;
	private ActivoEnum activo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaCreacion;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaModificacion;
	private String urlIntegracion;

}

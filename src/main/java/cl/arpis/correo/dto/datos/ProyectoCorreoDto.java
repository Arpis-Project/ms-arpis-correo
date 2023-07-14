package cl.arpis.correo.dto.datos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @author steph
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(Include.NON_NULL)
public class ProyectoCorreoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 596505227003887719L;

	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaCreacion;
	private Integer idTemplate;
	private Integer numeroTienda;
	private EtapaProyectoDto etapa;
	private CorreoDto correo;
	private TipoCorreoDto tipoCorreo;
	private TipoReceptorDto tipoReceptor;

}

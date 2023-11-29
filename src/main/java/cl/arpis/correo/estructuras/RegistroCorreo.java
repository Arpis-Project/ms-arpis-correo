package cl.arpis.correo.estructuras;

import java.util.Date;

import cl.arpis.correo.enums.EstadoRegistroEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author arpis
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroCorreo {

	private Integer id;
	private Date fechaMensaje;
	private String destinatarios;
	private String conCopia;
	private String contenido;
	private EstadoRegistroEnum estado;
	private String detalle;

}

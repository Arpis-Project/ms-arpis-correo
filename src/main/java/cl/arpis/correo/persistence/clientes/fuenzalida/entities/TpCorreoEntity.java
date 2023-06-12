package cl.arpis.correo.persistence.clientes.fuenzalida.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "API_CC_TP_CORREOS")
@Data
@NoArgsConstructor
public class TpCorreoEntity {

	@Id
	@Column(name = "ID_CORREO", nullable = false)
	private Long idCorreo;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "ID_TIPO_CORREO", nullable = false)
	private Long idTipoCorreo;

	@Column(name = "PASS", nullable = false)
	private String pasword;

	@Column(name = "ACTIVO", nullable = false)
	private String activo;

}

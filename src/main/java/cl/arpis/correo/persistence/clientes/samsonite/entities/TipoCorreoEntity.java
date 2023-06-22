package cl.arpis.correo.persistence.clientes.samsonite.entities;

import cl.arpis.correo.enums.ActivoEnum;
import cl.arpis.correo.enums.TipoCorreoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "REPORTUSER", name = "AP_EMAIL_TP_TIPO_CORREO")
@Data
@NoArgsConstructor
public class TipoCorreoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPO_CORREO")
	@SequenceGenerator(schema = "REPORTUSER", sequenceName = "SEQ_AP_EMAIL_TP_TIPO_CORREO",
		allocationSize = 1, name = "SEQ_TIPO_CORREO")
	@Column(name = "ID", nullable = false)
	private Short id;

	@Column(name = "NOMBRE", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoCorreoEnum nombre;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "ACTIVO", nullable = false)
	@Enumerated(EnumType.STRING)
	private ActivoEnum activo;

}

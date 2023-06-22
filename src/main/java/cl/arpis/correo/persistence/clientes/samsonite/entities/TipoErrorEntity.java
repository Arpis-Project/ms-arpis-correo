package cl.arpis.correo.persistence.clientes.samsonite.entities;

import cl.arpis.correo.enums.ActivoEnum;
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
@Table(schema = "REPORTUSER", name = "AP_EMAIL_TP_TIPO_ERROR")
@Data
@NoArgsConstructor
@Deprecated
public class TipoErrorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPO_ERROR")
	@SequenceGenerator(schema = "REPORTUSER", sequenceName = "SEQ_AP_EMAIL_TP_TIPO_ERROR",
		allocationSize = 1, name = "SEQ_TIPO_ERROR")
	@Column(name = "ID", nullable = false)
	private Short id;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "ACTIVO", nullable = false)
	@Enumerated(EnumType.STRING)
	private ActivoEnum activo;

}

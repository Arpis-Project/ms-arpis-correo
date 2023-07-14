package cl.arpis.correo.persistence.clientes.samsonite.entities;

import cl.arpis.correo.enums.ActivoEnum;
import cl.arpis.correo.enums.TipoReceptorEnum;
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
@Table(schema = "REPORTUSER", name = "AP_EMAIL_TP_TIPO_RECEPTOR")
@Data
@NoArgsConstructor
public class TipoReceptorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPO_RECEPTOR")
	@SequenceGenerator(schema = "REPORTUSER", sequenceName = "SEQ_AP_EMAIL_TP_TIPO_RECEPTOR",
		allocationSize = 1, name = "SEQ_TIPO_RECEPTOR")
	@Column(name = "ID", nullable = false)
	private Short id;

	@Column(name = "NOMBRE", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoReceptorEnum nombre;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "ACTIVO", nullable = false)
	@Enumerated(EnumType.STRING)
	private ActivoEnum activo;

}

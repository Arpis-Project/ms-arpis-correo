package cl.arpis.correo.persistence.clientes.samsonite.entities;

import cl.arpis.correo.enums.ActivoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "REPORTUSER", name = "AP_EMAIL_TD_CORREOS")
@Data
@NoArgsConstructor
public class CorreoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CORREOS")
	@SequenceGenerator(schema = "REPORTUSER", sequenceName = "SEQ_AP_EMAIL_TD_CORREOS",
		allocationSize = 1, name = "SEQ_CORREOS")
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "PASSWORD")
	private String pasword;

	@Column(name = "ACTIVO", nullable = false)
	@Enumerated(EnumType.STRING)
	private ActivoEnum activo;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_CORREO", nullable = false, updatable = true, insertable = true)
	private TipoCorreoEntity tipoCorreo;

}

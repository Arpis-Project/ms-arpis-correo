package cl.arpis.correo.persistence.clientes.samsonite.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "REPORTUSER", name = "AP_EMAIL_TD_PROY_CORREOS")
@Data
@NoArgsConstructor
public class ProyectoCorreoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROYECTO_CORREO")
	@SequenceGenerator(schema = "REPORTUSER", sequenceName = "SEQ_AP_EMAIL_TD_PROY_CORREOS",
		allocationSize = 1, name = "SEQ_PROYECTO_CORREO")
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "ID_PROYECTO", nullable = false)
	private Integer idProyecto;

	@Column(name = "ID_CORREO", nullable = false)
	private Integer idCorreo;

	@Column(name = "ID_TIPO_ENVIO", nullable = false)
	private Short idTipoEnvio;

	@Column(name = "FECHA_CREACION", nullable = false)
	@CreationTimestamp
	private Date fechaCreacion;

}

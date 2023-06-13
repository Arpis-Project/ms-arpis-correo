package cl.arpis.correo.persistence.clientes.samsonite.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(schema = "REPORTUSER", name = "AP_EMAIL_TD_PROY_ERROR")
@Data
@NoArgsConstructor
public class ProyectoErrorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROYECTO_ERROR")
	@SequenceGenerator(schema = "REPORTUSER", sequenceName = "SEQ_AP_EMAIL_TD_PROY_ERROR",
		allocationSize = 1, name = "SEQ_PROYECTO_ERROR")
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "FECHA_CREACION", nullable = false)
	@CreationTimestamp
	private Date fechaCreacion;

	@ManyToOne
	@JoinColumn(name = "ID_PROYECTO", nullable = false, updatable = true, insertable = true)
	private ProyectoEntity proyecto;

	@ManyToOne
	@JoinColumn(name = "ID_CORREO", nullable = false, updatable = true, insertable = true)
	private CorreoEntity correo;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_ENVIO", nullable = false, updatable = true, insertable = true)
	private TipoEnvioEntity tipoEnvio;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_ERROR", nullable = false, updatable = true, insertable = true)
	private TipoErrorEntity tipoError;

}

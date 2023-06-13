package cl.arpis.correo.persistence.clientes.fuenzalida.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(schema = "REPORTUSER", name = "AP_EMAIL_TD_USUARIOS")
@Data
@NoArgsConstructor
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
	@SequenceGenerator(schema = "REPORTUSER", sequenceName = "SEQ_AP_EMAIL_TD_USUARIOS",
		allocationSize = 1, name = "SEQ_USUARIO")
	@Column(name = "ID", nullable = false)
	private Short id;

	@Column(name = "LOGIN", nullable = false)
	private String login;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "NOMBRE_EMPRESA")
	private String nombreEmpresa;

	@Column(name = "ACTIVO", nullable = false)
	@Enumerated(EnumType.STRING)
	private ActivoEnum activo;

	@Column(name = "FECHA_CREACION", nullable = false)
	@CreationTimestamp
	private Date fechaCreacion;

	@Column(name = "FECHA_MODIFICACION", nullable = false)
	@UpdateTimestamp
	private Date fechaModificacion;

	@Column(name = "URL_INTEGRACION")
	private String urlIntegracion;

}

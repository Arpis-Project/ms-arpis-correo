package cl.arpis.correo.persistence.clientes.levis.entities;

import java.util.Date;

import cl.arpis.correo.enums.ActivoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "api_usuario", schema = "arpis")
@Data
@NoArgsConstructor
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario_api")
	private Long id;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;

	@Column(name = "nombre_empresa")
	private String nombreEmpresa;

	@Column(name = "activo")
	@Enumerated(EnumType.STRING)
	private ActivoEnum activo;

	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Column(name = "url_integracion")
	private String urlIntegracion;

}

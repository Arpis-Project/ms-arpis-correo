package cl.arpis.correo.persistence.clientes.levis.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class ApiUsuariosEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario_api")
	private Long idUsuarioApi;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;

	@Column(name = "nombre_empresa")
	private String nombreEmpresa;

	@Column(name = "activo")
	private String activo;

	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Column(name = "url_integracion")
	private String urlIntegracion;

}

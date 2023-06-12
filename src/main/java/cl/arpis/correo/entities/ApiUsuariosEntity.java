package cl.arpis.correo.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "api_usuario", schema = "arpis")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
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
	private char activo;

	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Column(name = "url_integracion")
	private String urlIntegracion;

}

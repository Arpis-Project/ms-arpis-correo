package cl.arpis.correo.persistence.clientes.levis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CorreoEntity {

	@Id
	@Column(name = "nombre", nullable = false)
	private String responsable;

	@Id
	@Column(name = "email", nullable = false)
	private String mail;

	@Column(name = "pass")
	private String pass;

}

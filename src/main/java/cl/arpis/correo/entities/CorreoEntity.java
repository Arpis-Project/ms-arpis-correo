package cl.arpis.correo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

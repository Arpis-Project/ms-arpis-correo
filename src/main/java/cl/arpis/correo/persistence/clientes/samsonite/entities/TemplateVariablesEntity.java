package cl.arpis.correo.persistence.clientes.samsonite.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author steph
 *
 */
@Entity
@Table(schema = "REPORTUSER", name = "AP_EMAIL_TP_VARIABLES")
@Data
@NoArgsConstructor
public class TemplateVariablesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEMPLATE_VARIABLES")
	@SequenceGenerator(schema = "REPORTUSER", sequenceName = "SEQ_AP_EMAIL_TP_VARIABLES",
		allocationSize = 1, name = "SEQ_TEMPLATE_VARIABLES")
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "ID_TEMPLATE", nullable = false)
	private Integer idTemplate;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "VALOR", nullable = false)
	private String valor;

}

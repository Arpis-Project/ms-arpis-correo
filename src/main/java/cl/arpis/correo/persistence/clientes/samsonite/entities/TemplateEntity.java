package cl.arpis.correo.persistence.clientes.samsonite.entities;

import java.util.Set;

import cl.arpis.correo.enums.ActivoEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(schema = "REPORTUSER", name = "AP_EMAIL_TP_TEMPLATES")
@Data
@NoArgsConstructor
public class TemplateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEMPLATES")
	@SequenceGenerator(schema = "REPORTUSER", sequenceName = "SEQ_AP_EMAIL_TP_TEMPLATES",
		allocationSize = 1, name = "SEQ_TEMPLATES")
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "CONTENIDO", nullable = false)
	private String contenido;

	@Column(name = "ACTIVO", nullable = false)
	@Enumerated(EnumType.STRING)
	private ActivoEnum activo;

	@OneToMany(mappedBy = "idTemplate", cascade = CascadeType.ALL)
	private Set<TemplateVariablesEntity> variables;

}

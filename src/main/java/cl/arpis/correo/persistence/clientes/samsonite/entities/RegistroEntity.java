package cl.arpis.correo.persistence.clientes.samsonite.entities;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import cl.arpis.correo.enums.EstadoRegistroEnum;
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

/**
 * 
 * @author arpis
 *
 */
@Entity
@Table(schema = "REPORTUSER", name = "AP_EMAIL_TD_REGISTRO")
@Data
@NoArgsConstructor
public class RegistroEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "FECHA_MENSAJE", nullable = false, updatable = true)
	@UpdateTimestamp
	private Date fechaMensaje;

	@Column(name = "DESTINATARIOS", nullable = false)
	private String destinatarios;

	@Column(name = "CON_COPIA")
	private String conCopia;

	@Column(name = "CONTENIDO", nullable = false)
	private String contenido;

	@Column(name = "ESTADO")
	@Enumerated(EnumType.STRING)
	private EstadoRegistroEnum estado;

	@Column(name = "DETALLE")
	private String detalle;

}

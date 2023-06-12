package cl.arpis.correo.service;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.MensajeEmailDto;

/**
 * 
 * @author steph
 *
 */
public interface EmailService {

	public void enviarResultados(MensajeDto correo, ContenedorCorreoDto contCorre);

	public void enviarEmail(MensajeDto correo, MensajeEmailDto mensaje, ContenedorCorreoDto contCorre);

}

package cl.arpis.correo.service;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.MensajeEmailDto;

public interface IEmailService {

	void enviarResultados(MensajeDto correo, ContenedorCorreoDto contCorre);

	void enviarEmail(MensajeDto correo, MensajeEmailDto mensaje, ContenedorCorreoDto contCorre);

}

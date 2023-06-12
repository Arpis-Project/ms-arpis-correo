package cl.arpis.correo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.CorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.RespuestaDto;
import cl.arpis.correo.exceptions.CorreoException;
import cl.arpis.correo.persistence.general.custom.CustomCorreosRepository;
import cl.arpis.correo.service.CorreoService;
import cl.arpis.correo.service.EmailService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CorreoServiceImpl implements CorreoService {

	private CustomCorreosRepository correoRepository;
	private EmailService mailService;

	public CorreoServiceImpl(CustomCorreosRepository correoRepository,
			EmailService mailService) {
		this.correoRepository = correoRepository;
		this.mailService = mailService;
	}

	@Override
	public RespuestaDto enviarCorreo(final MensajeDto correo) {
		ContenedorCorreoDto contCorreo = null;
		if(null == correo.getCodError()) {
			contCorreo = buscarCorreos(correo.getProyecto());
		} else {
			contCorreo = buscarCorreos(correo.getProyecto(),correo.getCodError());
		}
		this.mailService.enviarResultados(correo, contCorreo);
		return RespuestaDto.builder()
				.mensaje("Correos enviados")
				.casillas(contCorreo)
				.build();
	}

	@Override
	public ContenedorCorreoDto buscarCorreos(Long idProyecto) {
		final List<CorreoDto> listaCorreo = this.correoRepository.buscarCorreos(idProyecto);
		if(listaCorreo.isEmpty()) {
			log.error(String.format("Sin correos para proyecto: %s", idProyecto));
			throw new CorreoException(String.format("Sin correos para proyecto: %s", idProyecto));
		}
		return ContenedorCorreoDto.builder().listaCorreo(listaCorreo).build();
	}

	@Override
	public ContenedorCorreoDto buscarCorreos(Long idProyecto, String error) {
		final List<CorreoDto> listaCorreo = this.correoRepository.buscarCorreos(idProyecto, error);
		if(listaCorreo.isEmpty()) {
			log.error(String.format("Sin correos para proyecto: %s", idProyecto));
			throw new CorreoException(String.format("Sin correos para proyecto: %s", idProyecto));
		}
		return ContenedorCorreoDto.builder().listaCorreo(listaCorreo).build();
	}

}

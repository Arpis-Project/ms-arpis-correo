package cl.arpis.correo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.CorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.RespuestaDto;
import cl.arpis.correo.exceptions.CorreoException;
import cl.arpis.correo.repositories.ICorreoRepository;
import cl.arpis.correo.service.ICorreoService;
import cl.arpis.correo.service.IEmailService;
import cl.arpis.correo.util.MapperUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CorreoService implements ICorreoService {

	private ICorreoRepository correoRepo;
	private IEmailService mailService;

	public CorreoService(ICorreoRepository correoRepo,
			IEmailService mailService) {
		this.correoRepo = correoRepo;
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
	public ContenedorCorreoDto buscarCorreos(long idProyecto, String error) {
		final List<CorreoDto> listaCorreo = MapperUtils.listToList(
				this.correoRepo.buscarCorreo(idProyecto, error), CorreoDto.class);
		if(listaCorreo.isEmpty()) {
			log.error(String.format("Sin correos para proyecto: %s", idProyecto));
			throw new CorreoException(String.format("Sin correos para proyecto: %s", idProyecto));
		}
		return ContenedorCorreoDto.builder().listaCorreo(listaCorreo).build();
	}

	@Override
	public ContenedorCorreoDto buscarCorreos(long idProyecto) {
		final List<CorreoDto> listaCorreo = MapperUtils.listToList(
				this.correoRepo.buscarCorreo(idProyecto), CorreoDto.class);
		if(listaCorreo.isEmpty()) {
			log.error(String.format("Sin correos para proyecto: %s", idProyecto));
			throw new CorreoException(String.format("Sin correos para proyecto: %s", idProyecto));
		}
		return ContenedorCorreoDto.builder().listaCorreo(listaCorreo).build();
	}

}

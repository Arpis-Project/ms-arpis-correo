package cl.arpis.correo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.CorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.RespuestaDto;
import cl.arpis.correo.repositories.ICorreoRepository;
import cl.arpis.correo.service.ICorreoService;
import cl.arpis.correo.service.IEmailService;
import cl.arpis.correo.util.MapperUtils;

@Service
public class CorreoService implements ICorreoService {
	private ICorreoRepository correoRepo;
	private IEmailService mailService;

	public CorreoService(ICorreoRepository correoRepo,IEmailService mailService) {
		this.correoRepo = correoRepo;
		this.mailService = mailService;
	}

	@Override
	public RespuestaDto enviarCorreo(MensajeDto correo) {
		ContenedorCorreoDto contCorreo ;
		if (correo.getCodError()==null) {
			contCorreo = buscarCorreos(correo.getProyecto());
		}else {
			contCorreo = buscarCorreos(correo.getProyecto(),correo.getCodError());
		}
		mailService.enviarResultados(correo, contCorreo);
		return null;
	}

	@Override
	public ContenedorCorreoDto buscarCorreos(long idPoryecto, String error) {
		List<CorreoDto> listaCorreo = MapperUtils.listToList(correoRepo.buscarCorreo(idPoryecto, error),
				CorreoDto.class);
		return ContenedorCorreoDto.builder().listaCorreo(listaCorreo).build();
	}

	@Override
	public ContenedorCorreoDto buscarCorreos(long idPoryecto) {
		List<CorreoDto> listaCorreo = MapperUtils.listToList(correoRepo.buscarCorreo(idPoryecto), CorreoDto.class);
		return ContenedorCorreoDto.builder().listaCorreo(listaCorreo).build();
	}
}

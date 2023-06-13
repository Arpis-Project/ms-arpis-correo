package cl.arpis.correo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.RespuestaDto;
import cl.arpis.correo.dto.datos.ProyectoCorreoDto;
import cl.arpis.correo.dto.datos.ProyectoErrorDto;
import cl.arpis.correo.enums.ActivoEnum;
import cl.arpis.correo.exceptions.CorreoException;
import cl.arpis.correo.persistence.general.custom.CorreosRepository;
import cl.arpis.correo.service.CorreoService;
import cl.arpis.correo.service.EmailService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CorreoServiceImpl implements CorreoService {

	private CorreosRepository correoRepository;
	private EmailService mailService;

	public CorreoServiceImpl(CorreosRepository correoRepository,
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
	public ContenedorCorreoDto buscarCorreos(Integer idProyecto) {
		List<ProyectoCorreoDto> listaDatos = this.correoRepository.obtenerProyectoCorreo(idProyecto);
		listaDatos = listaDatos.stream()
			.filter(d -> ActivoEnum.S.equals(d.getCorreo().getActivo()))
			.filter(d -> ActivoEnum.S.equals(d.getProyecto().getActivo()))
			.filter(d -> ActivoEnum.S.equals(d.getTipoEnvio().getActivo()))
			.toList();
		if(listaDatos.isEmpty()) {
			log.error(String.format("Sin correos para proyecto: %s", idProyecto));
			throw new CorreoException(String.format("Sin correos para proyecto: %s", idProyecto));
		}
		return ContenedorCorreoDto.builder()
				.listaCorreo(listaDatos.stream().map(ProyectoCorreoDto::getCorreo).toList())
				.build();
	}

	@Override
	public ContenedorCorreoDto buscarCorreos(Integer idProyecto, Short idTipoError) {
		List<ProyectoErrorDto> listaDatos = this.correoRepository.obtenerProyectoErrorPorTipo(idProyecto, idTipoError);
		listaDatos = listaDatos.stream()
				.filter(d -> ActivoEnum.S.equals(d.getCorreo().getActivo()))
				.filter(d -> ActivoEnum.S.equals(d.getProyecto().getActivo()))
				.filter(d -> ActivoEnum.S.equals(d.getTipoEnvio().getActivo()))
				.filter(d -> ActivoEnum.S.equals(d.getTipoError().getActivo()))
				.toList();
		if(listaDatos.isEmpty()) {
			log.error(String.format("Sin correos para proyecto: %s", idProyecto));
			throw new CorreoException(String.format("Sin correos para proyecto: %s", idProyecto));
		}
		return ContenedorCorreoDto.builder()
				.listaCorreo(listaDatos.stream().map(ProyectoErrorDto::getCorreo).toList())
				.build();
	}

}

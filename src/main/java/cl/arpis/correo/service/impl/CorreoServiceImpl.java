package cl.arpis.correo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import cl.arpis.correo.dto.ContenedorCorreoDto;
import cl.arpis.correo.dto.MensajeDto;
import cl.arpis.correo.dto.RespuestaDto;
import cl.arpis.correo.dto.datos.ProyectoCorreoDto;
import cl.arpis.correo.dto.datos.ProyectoErrorDto;
import cl.arpis.correo.dto.datos.TemplateDTO;
import cl.arpis.correo.enums.ActivoEnum;
import cl.arpis.correo.exceptions.CorreoException;
import cl.arpis.correo.persistence.clientes.samsonite.entities.TemplateEntity;
import cl.arpis.correo.persistence.clientes.samsonite.repositories.TemplateRepository;
import cl.arpis.correo.persistence.general.custom.CorreosRepository;
import cl.arpis.correo.service.CorreoService;
import cl.arpis.correo.service.EmailService;
import cl.arpis.correo.util.JsonUtils;
import cl.arpis.correo.util.MapperUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CorreoServiceImpl implements CorreoService {

	private CorreosRepository correoRepository;
	private TemplateRepository templateRepository;
	private EmailService mailService;

	public CorreoServiceImpl(CorreosRepository correoRepository,
			TemplateRepository templateRepository,
			EmailService mailService) {
		this.correoRepository = correoRepository;
		this.templateRepository = templateRepository;
		this.mailService = mailService;
	}

	@Override
	public RespuestaDto enviarCorreo(final MensajeDto correo) {
		log.debug(JsonUtils.objectToJsonString(correo));
		ContenedorCorreoDto contCorreo = null;
		if(ObjectUtils.isEmpty(correo.getCodError())) {
			contCorreo = buscarCorreos(correo.getProyecto(), correo.getIdEtapa());
		} else {
			contCorreo = buscarCorreos(correo.getProyecto(), correo.getCodError());
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
			.filter(d -> ActivoEnum.S.equals(d.getEtapa().getActivo()))
			.filter(d -> ActivoEnum.S.equals(d.getEtapa().getProyecto().getActivo()))
			.filter(d -> ActivoEnum.S.equals(d.getTipoCorreo().getActivo()))
			.toList();
		if(listaDatos.isEmpty()) {
			log.error(String.format("Sin correos para proyecto: %s", idProyecto));
			throw new CorreoException(String.format("Sin correos para proyecto: %s", idProyecto));
		}
		return ContenedorCorreoDto.builder()
				.listaCorreo(listaDatos)
				.build();
	}

	@Override
	public ContenedorCorreoDto buscarCorreos(final Integer idProyecto, final Long idEtapa) {
		List<ProyectoCorreoDto> listaDatos = this.correoRepository.obtenerProyectoCorreo(idProyecto, idEtapa);
		listaDatos = listaDatos.stream()
			.filter(d -> ActivoEnum.S.equals(d.getCorreo().getActivo()))
			.filter(d -> ActivoEnum.S.equals(d.getEtapa().getActivo()))
			.filter(d -> ActivoEnum.S.equals(d.getEtapa().getProyecto().getActivo()))
			.filter(d -> ActivoEnum.S.equals(d.getTipoCorreo().getActivo()))
			.toList();
		if(listaDatos.isEmpty()) {
			log.error(String.format("Sin correos para proyecto: %s", idProyecto));
			throw new CorreoException(String.format("Sin correos para proyecto: %s", idProyecto));
		}
		return ContenedorCorreoDto.builder()
				.listaCorreo(listaDatos)
				.build();
	}

	@Override
	public Optional<TemplateDTO> obtenerTemplate(final Integer idTemplate) {
		final Optional<TemplateEntity> template = this.templateRepository.findById(idTemplate);
		if(template.isPresent()) {
			return Optional.of(MapperUtils.objectToObject(template.get(), TemplateDTO.class));
		}
		return Optional.empty();
	}

	@Override
	@Deprecated
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
				.listaCorreoError(listaDatos)
				.build();
	}

}

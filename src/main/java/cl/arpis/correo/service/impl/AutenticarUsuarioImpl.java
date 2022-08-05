package cl.arpis.correo.service.impl;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.arpis.correo.repositories.ApiUsuariosRepository;
import cl.arpis.correo.entities.ApiUsuariosEntity;
import cl.arpis.correo.service.IAutenticarUsuario;

@Service
@Transactional
public class AutenticarUsuarioImpl implements IAutenticarUsuario {

	private ApiUsuariosRepository usuarioRepo;

	public AutenticarUsuarioImpl(ApiUsuariosRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}

	@Override
	public ApiUsuariosEntity buscarUsuario(String usuario) {
		List<ApiUsuariosEntity> listUsuario = usuarioRepo.findByLogin(usuario);
		if (listUsuario.size() == 1) {
			return listUsuario.get(0);
		}
		throw new UsernameNotFoundException("Error de usuario");
	}
}

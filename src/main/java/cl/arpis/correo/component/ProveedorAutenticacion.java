package cl.arpis.correo.component;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import cl.arpis.correo.service.IAutenticarUsuario;
import cl.arpis.correo.entities.ApiUsuariosEntity;


@Component("autenticacion")
public class ProveedorAutenticacion implements AuthenticationProvider {

	private IAutenticarUsuario autenticarUsuario;

	public ProveedorAutenticacion(IAutenticarUsuario autenticarUsuario) {
		this.autenticarUsuario = autenticarUsuario;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String pass = authentication.getCredentials().toString();
		ApiUsuariosEntity usuario = autenticarUsuario.buscarUsuario(authentication.getName());
		if (usuario.getPassword().equals(pass)) {
			return new UsernamePasswordAuthenticationToken(usuario.getLogin(), pass, new ArrayList<>());
		}
		return null;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}

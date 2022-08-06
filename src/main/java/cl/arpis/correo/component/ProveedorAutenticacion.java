package cl.arpis.correo.component;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionException;

import cl.arpis.correo.entities.ApiUsuariosEntity;
import cl.arpis.correo.service.IAutenticarUsuario;
import lombok.extern.slf4j.Slf4j;

@Component("autenticacion")
@Slf4j
public class ProveedorAutenticacion implements AuthenticationProvider {

	private IAutenticarUsuario autenticarUsuario;

	public ProveedorAutenticacion(IAutenticarUsuario autenticarUsuario) {
		this.autenticarUsuario = autenticarUsuario;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("Iniciando sesión");
		String pass = authentication.getCredentials().toString();
		try {
			ApiUsuariosEntity usuario = this.autenticarUsuario.buscarUsuario(authentication.getName());
			if (usuario.getPassword().equals(pass)) {
				log.info("Sesión iniciada");
				return new UsernamePasswordAuthenticationToken(usuario.getLogin(), pass, new ArrayList<>());
			}
		} catch(TransactionException ex) {
			throw new RuntimeException("Problemas con la autenticación accediendo a la DB", ex);
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}

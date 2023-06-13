package cl.arpis.correo.component;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionException;

import cl.arpis.correo.dto.datos.UsuarioDto;
import cl.arpis.correo.exceptions.ArpisException;
import cl.arpis.correo.persistence.general.custom.CorreosRepository;
import lombok.extern.slf4j.Slf4j;

@Component("autenticacion")
@Slf4j
public class ProveedorAutenticacion implements AuthenticationProvider {

	private CorreosRepository correosRepository;

	public ProveedorAutenticacion(CorreosRepository correosRepository) {
		this.correosRepository = correosRepository;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String pass = authentication.getCredentials().toString();
		try {
			Optional<UsuarioDto> usuario = this.correosRepository.buscarUsuario(authentication.getName());
			if(usuario.isEmpty()) {
				return null;
			}
			if (usuario.get().getPassword().equals(pass)) {
				log.info("Sesión iniciada para {}", authentication.getName());
				return new UsernamePasswordAuthenticationToken(usuario.get().getLogin(), authentication.getCredentials(),
						new ArrayList<>());
			}
		} catch(TransactionException ex) {
			throw new ArpisException("Problemas con la autenticación accediendo a la DB", ex);
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

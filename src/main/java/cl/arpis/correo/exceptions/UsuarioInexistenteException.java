package cl.arpis.correo.exceptions;

/**
 * 
 * @author steph
 *
 */
public class UsuarioInexistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8744518008956714015L;

	public UsuarioInexistenteException(String message) {
		super(message);
	}

	public UsuarioInexistenteException(String message, Throwable ex) {
		super(message, ex);
	}

}

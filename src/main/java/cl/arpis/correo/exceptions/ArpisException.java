package cl.arpis.correo.exceptions;

/**
 * 
 * @author steph
 *
 */
public class ArpisException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4597898360568858931L;

	public ArpisException(String message) {
		super(message);
	}

	public ArpisException(String message, Throwable ex) {
		super(message, ex);
	}

}

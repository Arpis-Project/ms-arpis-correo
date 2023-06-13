package cl.arpis.correo.exceptions;

/**
 * 
 * @author steph
 *
 */
public class CorreoDbException extends ArpisException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7977809235441241473L;

	public CorreoDbException(String message) {
		super(message);
	}

	public CorreoDbException(String message, Throwable ex) {
		super(message, ex);
	}

}

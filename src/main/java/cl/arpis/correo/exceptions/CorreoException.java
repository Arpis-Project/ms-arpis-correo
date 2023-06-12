package cl.arpis.correo.exceptions;

/**
 * 
 * @author steph
 *
 */
public class CorreoException extends ArpisException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1514167937316784440L;

	public CorreoException(String message) {
		super(message);
	}

	public CorreoException(String message, Throwable ex) {
		super(message, ex);
	}

}

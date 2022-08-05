package cl.arpis.correo.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CorreoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4505475403835867454L;

	public CorreoException(String detalle) {
		super(detalle);
	}

	public CorreoException(String detalle, Throwable ex) {
		super(detalle, ex);
	}

}

package exceptions;

public class StatusUsuarioException extends Exception {
	private static final long serialVersionUID = 1L;

	public StatusUsuarioException(String status) {
		super("Usuario ja eh " + status);
	}

}

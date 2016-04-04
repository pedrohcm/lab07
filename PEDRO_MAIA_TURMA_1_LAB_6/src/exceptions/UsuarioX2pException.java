package exceptions;

public class UsuarioX2pException extends Exception {
	private static final long serialVersionUID = 1L;

	public UsuarioX2pException() {
		super("Usuario nao tem x2p suficiente para realizar o upgrade");
	}
	
}

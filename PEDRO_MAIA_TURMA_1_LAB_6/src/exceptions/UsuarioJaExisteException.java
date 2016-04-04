package exceptions;

public class UsuarioJaExisteException extends DadosInvalidosException {
	
	public UsuarioJaExisteException() {
		super("Usuario ja existe");
		
	}

	private static final long serialVersionUID = 1L;

}

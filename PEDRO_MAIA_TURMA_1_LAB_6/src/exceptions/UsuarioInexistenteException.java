/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package exceptions;

public class UsuarioInexistenteException extends DadosInvalidosException {
	private static final long serialVersionUID = 1L;
	
	public UsuarioInexistenteException() {
		super("Usuario nao existe");
	}
}

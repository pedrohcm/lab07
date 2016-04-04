/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package exceptions;

public class DadosInvalidosException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public DadosInvalidosException(String mensagem) {
		super(mensagem);
	}

}

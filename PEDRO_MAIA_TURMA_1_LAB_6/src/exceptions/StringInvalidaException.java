/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package exceptions;

public class StringInvalidaException extends DadosInvalidosException {
	private static final long serialVersionUID = 1L;
	
	public StringInvalidaException() {
		super("String nao pode ser null ou vazia");
	}
	
	public StringInvalidaException(String mensagem) {
		super(mensagem);
	}
}

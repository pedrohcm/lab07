/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package exceptions;

public class StringInvalidaException extends DadosInvalidosException {
	private static final long serialVersionUID = 1L;
	
	public StringInvalidaException(String tipo) {
		super(tipo + "nao pode ser null ou vazio");
	}
	
	public StringInvalidaException(String mensagem) {
		super(mensagem);
	}
}

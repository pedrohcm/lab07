/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package exceptions;

public class ValorInvalidoException extends DadosInvalidosException {
	private static final long serialVersionUID = 1L;
	
	public ValorInvalidoException() {
		super("Valor nao pode ser negativo");
		}
}

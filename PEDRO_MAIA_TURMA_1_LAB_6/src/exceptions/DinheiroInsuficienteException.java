/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package exceptions;

public class DinheiroInsuficienteException extends DadosInvalidosException {
	private static final long serialVersionUID = 1L;
	
	public DinheiroInsuficienteException() {
		super("Dinheiro insuficiente para a compra do jogo");
	}

}

/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package exceptions;

public class JogoInexistenteException extends DadosInvalidosException {
	private static final long serialVersionUID = 1L;
	
	public JogoInexistenteException() {
		super("Jogo nao existe na biblioteca de jogos");
	}

}

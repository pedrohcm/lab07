/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package exceptions;

public class JogoJaExisteException extends DadosInvalidosException {
	private static final long serialVersionUID = 1L;
	
		public JogoJaExisteException() {
			super("Jogo ja existe na biblioteca");
		}
		
		public JogoJaExisteException(String mensagem) {
			super(mensagem);
		}
}


/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package p2cg;

import java.util.HashSet;

import exceptions.DadosInvalidosException;

/**
 * Subclasse de Jogo que contem metodos responsaveis pelo
 * comportamento de jogos de RPG.
 * @author Pedro Maia
 */
public class RPG extends Jogo {
	
	/** Construtor da classe que apenas chama o construtor da superclasse 
	 */
	public RPG(String nome, double preco, HashSet<Jogabilidade> jogabilidade) throws DadosInvalidosException {
		super(nome, preco, jogabilidade);
	}
	
	/** Chama o metodo da superclasse e o calculax2p de sua propria classe.
	 * @return o x2p calculado
	 */
	@Override
	public int registraJogada(int score, boolean zerou) throws DadosInvalidosException {
		super.registraJogada(score, zerou);
		return calculax2p(score, zerou);
	}
	
	/** Metodo que calcula o x2p a ser retornado;
	 * @return sempre 10.
	 */
	@Override
	public int calculax2p(int score, boolean zerou) {
		return 10;
	}
	
	/**
	 * Metodo que cria uma string com o nome do jogo, alem das informacoes
	 * sobre o mesmo (quantidade jogada, zerada, etc).
	 * @return string criada.
	 */
	@Override
	public String toString() {
		String texto = String.format("+ %s - RPG:\n", getNome());
		texto += super.toString();
		return texto;
	}

}

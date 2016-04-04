/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package p2cg;

import java.util.HashSet;

import exceptions.DadosInvalidosException;

/**
 * Subclasse de Jogo que contem metodos responsaveis pelo
 * comportamento de jogos de Plataforma.
 * @author Pedro Maia
 */
public class Plataforma extends Jogo {

	/** Construtor da classe que apenas chama o construtor da superclasse
	 */
	public Plataforma(String nome, double preco, HashSet<Jogabilidade> jogabilidade) throws DadosInvalidosException {
		super(nome, preco, jogabilidade);
	}
	
	/** Chama o metodo da superclasse e o calculax2p de sua propria classe.
	 */
	@Override
	public int registraJogada(int score, boolean zerou) throws DadosInvalidosException {
		super.registraJogada(score, zerou);
		return calculax2p(score, zerou);
	}
	
	/** Metodo que calcula o x2p a ser retornado;
	 * @zerou se for [true], retorna 20. se não, retorna apenas zero.
	 */
	@Override
	public int calculax2p(int score, boolean zerou) {
		if(zerou) {
			return 20;
		}
		return 0;
	}
	
	/**
	 * Metodo que cria uma string com o nome do jogo, alem das informacoes
	 * sobre o mesmo (quantidade jogada, zerada, etc).
	 * @return string criada.
	 */
	@Override
	public String toString() {
		String texto = String.format("+ %s - Plataforma:\n", getNome());
		texto += super.toString();
		return texto;
	}

}

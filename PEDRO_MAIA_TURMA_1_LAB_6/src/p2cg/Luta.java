/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package p2cg;

import java.util.HashSet;

import exceptions.DadosInvalidosException;

/**
 * Subclasse de Jogo que contem metodos responsaveis pelo
 * comportamento de jogos de Luta.
 * @author Pedro Maia
 */
public class Luta extends Jogo {
	private final int VALOR_MAXIMO_X2P = 100000;
	
	/** Construtor da classe que apenas chama o construtor da superclasse
	 */
	public Luta(String nome, double preco, HashSet<Jogabilidade> jogabilidade) throws DadosInvalidosException{
		super(nome, preco, jogabilidade);
	}
	
	/** Chama o metodo da superclasse e o calculax2p de sua propria classe.
	 * @param score se for maior que 100000, o maiorScore continuara 100000, visto que eh o valor maximo.
	 * @return x2p calculado
	 */
	@Override
	public int registraJogada(int score, boolean zerou) throws DadosInvalidosException {
		if((score) > VALOR_MAXIMO_X2P) {
			score = VALOR_MAXIMO_X2P;
		}
		int x2p = calculax2p(score, zerou);
		super.registraJogada(score, zerou);
		return x2p;
	}
	
	/** Metodo que calcula o x2p a ser retornado;
	 * @score o score registrado na jogada
	 * @return 1 para cada 1000 pontos de diferenca entre o score
	 * e o maiorScore. Se nao for maior, retorna zero.
	 */
	@Override
	public int calculax2p(int score, boolean zerou) {
		if(score > getMaiorScore()) {
			return score / 1000;
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
		String texto = String.format("+ %s - Luta:\n", getNome());
		texto += super.toString();
		return texto;
	}
	
}

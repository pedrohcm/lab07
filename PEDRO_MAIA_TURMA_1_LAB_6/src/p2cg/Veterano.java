/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package p2cg;

import java.util.HashMap;

/**
 * Subclasse de Usuario que contem metodos responsaveis pelo
 * comportamento de Usuarios Veteranos.
 * @author Pedro Maia
 */
public class Veterano implements TipoDeUsuarioIF {

	public int recompensar(Jogo game) {
		int x2p = 0;
		for(Jogabilidade estilo: game.getJogabilidade()) {
			if(estilo == Jogabilidade.ONLINE) {
				x2p += 10;
			}
			if(estilo == Jogabilidade.COOPERATIVO) {
				x2p += 20;
			}
		}
		return x2p;
	}
	
	public int punir(Jogo game) {
		int x2p = 0;
		for(Jogabilidade estilo: game.getJogabilidade()) {
			if(estilo == Jogabilidade.COOPERATIVO) {
				x2p -= 20;
			}
			if(estilo == Jogabilidade.COMPETITIVO) {
				x2p -= 20;
			}
		}
		return x2p;
	}
	
	/**
	 * Metodo que recebe o objeto do jogo a ser comprado e retorna uma lista com 
	 * o valor do jogo com 20% de desconto e com o valor de x2p (15 a cada 1 real do jogo)
	 * @jogo recebe e calcula o preco com 20% de desconto
	 * @return HashMap com a info da compra
	 */
	public HashMap<String, Double> compraJogo(Jogo game) {
		HashMap<String, Double> infoCompra = new HashMap<String, Double>();
		infoCompra.put("valor", game.getPreco() * 0.8);
		infoCompra.put("x2p", game.getPreco().intValue() * 15.0);
		return infoCompra;
	}

}

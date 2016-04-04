/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package factory;

import java.util.HashSet;

import exceptions.DadosInvalidosException;
import p2cg.Jogabilidade;
import p2cg.Jogo;
import p2cg.Luta;
import p2cg.Plataforma;
import p2cg.RPG;

/**
 * Classe responsavel pela criacao de jogos. Usada em loja para a criacao
 * de copias de jogos a serem vendidos.
 * @author Pedro Maia
 */
public class FactoryDeJogos {
	
	/**
	 * Metodo que cria um objeto do tipo Jogo e o retorna.
	 * @param nome string com o nome do jogo
	 * @param preco double com o preco do jogo
	 * @param tipo string com o tipo do jogo (rpg, plataforma ou luta)
	 * @param colecao de estilos de jogabilidade
	 * @return o objeto criado
	 * @throws DadosInvalidosException caso alguma das informacoes recebidas sejam invalidas
	 */
	public Jogo criaJogo(String nome, double preco, String tipo, HashSet<Jogabilidade> jogabilidade) throws DadosInvalidosException {
		if(tipo.equalsIgnoreCase("rpg")) {
			return criaJogoRPG(nome, preco, jogabilidade);
		}
		
		if(tipo.equalsIgnoreCase("luta")) {
			return criaJogoLuta(nome, preco, jogabilidade);
		}
		
		if(tipo.equalsIgnoreCase("plataforma")) {
			return criaJogoPlataforma(nome, preco, jogabilidade);
		}
		return null;
	}
	
	/**
	 * Metodo que cria um jogo de RPG
	 * @return jogo criado
	 */
	private Jogo criaJogoRPG(String nome, double preco, HashSet<Jogabilidade> jogabilidade) throws DadosInvalidosException {
		Jogo rpg = new RPG(nome, preco, jogabilidade);
		return rpg;
	}
	
	/**
	 * Metodo que cria um jogo de Luta
	 * @return jogo criado
	 */
	private Jogo criaJogoLuta(String nome, double preco, HashSet<Jogabilidade> jogabilidade) throws DadosInvalidosException {
		Jogo luta = new Luta(nome, preco, jogabilidade);
		return luta;
	}
	
	/**
	 * Metodo que cria um jogo de Plataforma
	 * @return jogo criado
	 */
	private Jogo criaJogoPlataforma(String nome, double preco, HashSet<Jogabilidade> jogabilidade) throws DadosInvalidosException {
		Jogo plataforma = new Plataforma(nome, preco, jogabilidade);
		return plataforma;
	}
	
}
	

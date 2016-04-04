/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package p2cg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import exceptions.DadosInvalidosException;
import exceptions.DinheiroInsuficienteException;
import exceptions.JogoInexistenteException;
import exceptions.JogoJaExisteException;
import exceptions.StringInvalidaException;
import exceptions.UsuarioInexistenteException;
import exceptions.UsuarioJaExisteException;

import factory.FactoryDeJogos;
import factory.FactoryDeUsuarios;

/** Classe responsavel por todas as atividades de criacao de usuarios, venda
 * de jogos e upgrade de usuarios.
 * @author Pedro Maia
 */
public class LojaController {
	private HashMap<String, Usuario> listaUsuarios;
	private ArrayList<Jogo> listaJogos;
	private FactoryDeJogos factoryJogo;
	private FactoryDeUsuarios factoryUsuario;
	
	/**
	 * Construtor da loja que nao tem nenhum parametro. Cria uma nova colecao
	 * para a lista de Usuarios e lista de Jogos. Tambem cria uma nova instancia de 
	 * CriaJogo, que sera responsavel pela criacao de jogos para venda.
	 */
	public LojaController() {
		listaUsuarios = new HashMap<String, Usuario>();
		listaJogos = new ArrayList<Jogo>();
		factoryJogo = new FactoryDeJogos();
		factoryUsuario = new FactoryDeUsuarios();
	}
	
	/**
	 * Metodo que adiciona um jogo a biblioteca de jogos da loja
	 * @param nome string com o nome do jogo a ser criado
	 * @param preco double com o preco do jogo a ser criado
	 * @param tipo string com o tipo do jogo a ser criado (rpg, luta ou plataforma)
	 * @param jogabilidade colecao com o estilos do jogo a ser criado
	 * @return boolean se o jogo foi adicionado
	 * @throws DadosInvalidosException caso o jogo ja exista, ou as informacoes recebidas forem invalidas
	 */
	public boolean criaJogo(String nome, double preco, String tipo, HashSet<Jogabilidade> jogabilidade) throws DadosInvalidosException {
			Jogo game = factoryJogo.criaJogo(nome, preco, tipo, jogabilidade);
			if(existeJogo(game)) {
				throw new JogoJaExisteException("Jogo ja existe na loja");
			}
			listaJogos.add(game);
			return true;
	}
	
	/**
	 * Metodo que adiciona um usuario a lista de usuarios da loja.
	 * @param login string com o login do usuario
	 * @param nomeUsuario string com nome do usuario
	 * @param tipo string com o tipo de usuario
	 * @return boolean se o usuario foi adicionado.
	 * @throws DadosInvalidosException 
	 */
	public boolean criaUsuario(String login, String nomeUsuario, String tipo) throws DadosInvalidosException {
		if(existeUsuario(login)) {
			throw new UsuarioJaExisteException();	
		}
		if(login == null || login.trim().equals("")) {
			throw new StringInvalidaException();
		}
		Usuario usuario = factoryUsuario.criaUsuario(nomeUsuario, login, tipo);
		listaUsuarios.put(login, usuario);
		return true;
	}
	
	/**
	 * Metodo que verifica se um usuario com o login especificado existe na loja.
	 * @param login string com o login do usuario
	 * @return boolean se o usuario existe ou nao.
	 */
	public boolean existeUsuario(String login) {
		if(listaUsuarios.containsKey(login)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo que retorna o objeto do tipo usuario, caso ele exista na loja
	 * @param login string com o login do usuario
	 * @return o usuario pesquisado
	 * @throws UsuarioInexistenteException caso o usuario nao exista
	 */
	private Usuario getUsuario(String login) throws DadosInvalidosException {
		if(existeUsuario(login)) {
			return listaUsuarios.get(login);
		}
		throw new UsuarioInexistenteException();
	}
	
	/**
	 * Metodo que retorna o objeto do tipo Jogo, caso ele exista na loja
	 * @param nome string com nome do jogo
	 * @return o jogo pesquisado
	 * @throws JogoInexistenteException caso o jogo nao exista
	 */
	private Jogo getJogo(String nome) throws DadosInvalidosException {
		for(Jogo game: listaJogos) {
			if(game.getNome().equals(nome)) {
				return game;
			}
		}
		throw new JogoInexistenteException();
	}
	
	/**
	 * Metodo que verifica se um jogo com o nome especificado existe na loja.
	 * @param jogo objeto do tipo Jogo
	 * @return boolean se o usuario existe ou nao.
	 */
	private boolean existeJogo(Jogo jogo) {
		if(listaJogos.contains(jogo)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo que adiciona dinheiro a um usuario especificado, caso ele exista
	 * @param login o login do usuario a ter o dinheiro adicionado
	 * @param quantia double com a quantia
	 * @return boolean se o dinheiro foi adicionado
	 * @throws DadosInvalidosException caso a quantia seja invalida
	 */
	public boolean adicionaDinheiroUsuario(String login, double quantia) throws DadosInvalidosException {
			if(existeUsuario(login)) {
				listaUsuarios.get(login).adicionaDinheiro(quantia);
				return true;
			}
			return false;
	}
	
	/**
	 * Metodo que vende um jogo a um determinado usuario. Caso o usuario exista, envia uma copia
	 * do jogo ao mesmo (que verificara se o jogo podera ser comprado).
	 * @param login string com o login do usuario
	 * @param nomeJogo string com o nome do jogo a ser vendido
	 * @param tipoJogo tipo do jogo a ser vendido
	 * @return boolean se o jogo foi vendido
	 * @throws DadosInvalidosException 
	 * @throws DinheiroInsuficienteException 
	 */
	public boolean vendeJogo(String login, String nomeJogo, String tipoJogo) throws DadosInvalidosException {
			if(!existeUsuario(login)) {
				throw new UsuarioInexistenteException();
			}
			Jogo game = getJogo(nomeJogo);
			Jogo copia = factoryJogo.criaJogo(game.getNome(), game.getPreco(), tipoJogo, game.getJogabilidade());
			listaUsuarios.get(login).compraJogo(copia);
			return true;
	}
	
	public boolean upgrade(String login) throws Exception {
		Usuario usuario = getUsuario(login);
		return usuario.realizaUpgrade();
	}
	
	public boolean downgrade(String login) throws Exception {
		Usuario usuario = getUsuario(login);
		return usuario.realizaDowngrade();
	}
	
	/**
	 * Metodo que retorna a colecao de usuarios da loja
	 * @return lista de usuarios
	 */
	public HashMap<String, Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	
	/**
	 * Metodo que cria uma string, e adiciona a ela todas as informacoes
	 * dos usuarios, incluindo seus jogos e as informacoes de cada jogo.
	 */
	@Override
	public String toString() {
		String texto = "=== Central P2-CG ===\n";
		for(String login: listaUsuarios.keySet()) {
			texto += String.format("%s\n", listaUsuarios.get(login));
		}
		texto += "--------------------------------------------";
		return texto;
	}
	
	/** Hashcode que funciona a partir dos mesmo atributos do Equals.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listaJogos == null) ? 0 : listaJogos.hashCode());
		result = prime * result + ((listaUsuarios == null) ? 0 : listaUsuarios.hashCode());
		return result;
	}
	
	/**
	 * Equals que define se dois objetos do tipo Loja sao iguais se
	 * ambos tiverem os mesmos usuarios e mesmos jogos.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof LojaController) {
			LojaController outraLoja = (LojaController) obj;
			if (listaJogos.equals(outraLoja.listaJogos)) {
				if(listaUsuarios.equals(outraLoja.listaUsuarios)) {
					return true;
				}
			}
		}
		return false;
	}
	
}

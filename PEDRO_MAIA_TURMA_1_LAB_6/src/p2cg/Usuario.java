/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package p2cg;

import java.util.HashMap;
import java.util.LinkedList;

import exceptions.DadosInvalidosException;
import exceptions.DinheiroInsuficienteException;
import exceptions.JogoInexistenteException;
import exceptions.JogoJaExisteException;
import exceptions.StatusUsuarioException;
import exceptions.StringInvalidaException;
import exceptions.UsuarioX2pException;
import exceptions.ValorInvalidoException;

/** Classe responsavel por abstrair os atributos e metodos inerentes as
 * classes Noob e Veterano.
 *  @author Pedro Maia
 */
public class Usuario {
	private String nome, login;
	private LinkedList<Jogo> colecaoJogos;
	private double dinheiro;
	private int x2pTotal;
	private TipoDeUsuarioIF statusUsuario;
	private final int X2P_META = 1000;
	
	/**
	 * Construtor que recebe um nome, cria um ArrayList vazia de colecaoJogos e atribui zero
	 * a quantidade de dinheiro disponivel e ao x2pTotal. Status inicial eh noob.
	 * @param nome recebe o nome o atribui a variavel do objeto.
	 * @throws NomeInvalidoException caso o nome (string) seja invalida
	 */
	public Usuario(String nome, String login) throws DadosInvalidosException {
		if(nome == null || nome.trim().equals("")) {
			throw new StringInvalidaException("Nome");
		}
		if(login == null || login.trim().equals("")) {
			throw new StringInvalidaException("Login");
		}
		this.nome = nome;
		this.login = login;
		colecaoJogos = new LinkedList<Jogo>();
		dinheiro = 0;
		x2pTotal = 0;
		statusUsuario = new Noob();
	}
	
	public boolean compraJogo(Jogo game) throws DadosInvalidosException {
		if(existeJogo(game)) {
			throw new JogoJaExisteException();
		}
		HashMap<String, Double> infoCompra = statusUsuario.compraJogo(game);
		double valorJogo = infoCompra.get("valor");
		int x2p = infoCompra.get("x2p").intValue();
		
		if(dinheiro < valorJogo) {
			throw new DinheiroInsuficienteException();
		}
		
		dinheiro -= valorJogo;
		x2pTotal += x2p;
		colecaoJogos.add(game);
		verificaMudanca();
		return true;
	}
	public boolean recompensar(String nomeJogo, int score, boolean zerou) throws DadosInvalidosException {
		Jogo game = getJogo(nomeJogo);
		x2pTotal += statusUsuario.recompensar(game);
		x2pTotal += game.registraJogada(score, zerou);
		verificaMudanca();
		return true;
	}
	
	public boolean punir(String nomeJogo, int score, boolean zerou) throws DadosInvalidosException {
		Jogo game = getJogo(nomeJogo);
		x2pTotal -= statusUsuario.punir(game);
		x2pTotal += game.registraJogada(score, zerou);
		verificaMudanca();
		return true;
	}
	
	private void verificaMudanca() {
		if (x2pTotal >= X2P_META) {
			statusUsuario = new Veterano();
		}
		if (x2pTotal < X2P_META) {
			statusUsuario = new Noob();
		}
	}
	
	/** Metodo que percorre na lista de jogos do usuario por um jogo que tenha
	 * o mesmo nome que o nome recebido como parametro.
	 * @param nomeJogo recebe o nome a ser verificado.
	 * @return o objeto do tipo Jogo encontrado.
	 * @throws Exception caso nao exista um jogo com o nome especificado.
	 */
	public Jogo getJogo(String nomeJogo) throws DadosInvalidosException {
		if(nomeJogo == null || nomeJogo.trim().equals("")) {
			throw new StringInvalidaException("Nome do jogo especificado");
		}
		for(Jogo jogo: colecaoJogos) {
			if(nomeJogo.equals(jogo.getNome())) {
				return jogo;
			}
		}
		throw new JogoInexistenteException();
	}
	
	public boolean existeJogo(Jogo jogo) {
		if(colecaoJogos.contains(jogo)) {
			return true;
		}
		return false;
	}
	
	/** Metodo que percorre toda a lista de jogosComprados e guarda
	 * o preco de cada um.
	 * @return a soma do preco de todos os jogos.
	 */
	public double calculaPreco() {
		double total = 0.00;
		for(Jogo jogo: colecaoJogos) {
			total += jogo.getPreco();
		}
		return total;
	}
	
	/**
	 * Metodo que adiciona dinheiro ao usuario
	 * @param quantia dinheiro a ser adicionado
	 * @return se o dinheiro foi adicionado
	 * @throws DadosInvalidosException caso o valor seja invalido
	 */
	public boolean adicionaDinheiro(double quantia) throws DadosInvalidosException {
		if(quantia < 0) {
			throw new ValorInvalidoException();
		}
		dinheiro = dinheiro + quantia;
		return true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}

	public LinkedList<Jogo> getJogos() {
		return colecaoJogos;
	}

	public void setJogosComprados(LinkedList<Jogo> jogosComprados) {
		this.colecaoJogos = jogosComprados;
	}

	public double getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}
	
	public String getExperiencia() {
		return statusUsuario.getClass().getSimpleName();
	}
	
	public int getX2pTotal() {
		return x2pTotal;
	}

	public void setX2pTotal(int x2pTotal) {
		this.x2pTotal = x2pTotal;
	}
	
	/**
	 * Metodo que cria uma string que vai conter todas as informacoes
	 * dos jogos e o total do preco deles.
	 * @return a string criada.
	 */
	@Override
	public String toString() {
		String texto = String.format("Jogador: %s: %s\n%s - %d x2p\nLista de Jogos:\n", getExperiencia(), login, nome, x2pTotal);
		for(Jogo jogo: getJogos()) {
			texto += jogo;
		}
		texto += String.format("Total de preco dos Jogos: R$ %.2f", calculaPreco());
		return texto;
	}

	/** Hashcode que funciona a partir dos mesmo atributos do Equals.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
	
	/** Equals que define se dois objetos do tipo Usuario sao iguais se
	 * ambos tiverem o mesmo nome, mesma quantidade de x2p e mesmo tipo
	 * de experiencia.
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Usuario) {
			Usuario outroUsuario = (Usuario) obj;
			if(nome.equals(outroUsuario.getNome())) {
				return true;
			}
		}
		return false;
	}
	
}

/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package p2cg;

import java.util.HashSet;

import exceptions.DadosInvalidosException;
import exceptions.StringInvalidaException;
import exceptions.ValorInvalidoException;

/**
 * Classe responsavel por abstrair os atributos e metodos inerentes as
 * classes Luta, Plataforma e RPG.
 * @author Pedro Maia
 */
public abstract class Jogo {
	private String nome;
	private double preco;
	private int maiorScore;
	private int quantJogada;
	private int quantZerada;
	private HashSet<Jogabilidade> jogabilidade;
	private Jogabilidade OFFLINE;
	private Jogabilidade MULTIPLAYER;
	private Jogabilidade ONLINE;
	private Jogabilidade COOPERATIVO;
	private Jogabilidade COMPETITIVO;
	
	 /** Construtor que recebe nome e preco, atribui 0 as variaveis maiorScore, quantidade de vezes que o jogo
	 * foi jogado, quantidade de vezes que o jogo foi zerado e cria um nova colecao que guarda seu estilo de jogabilidade.
	 * @param nome recebe o nome e o atribui ao nome do objeto
	 * @param preco recebe o preco e o atrubui ao preco do objeto
	 * @throws Exception caso o nome seja null ou vazio e caso o preco for negativo.
	 */
	public Jogo (String nome, double preco, HashSet<Jogabilidade> jogabilidade) throws DadosInvalidosException {
		if(nome == null || nome.trim().equals("")) {
			throw new StringInvalidaException();
		}
		if(preco < 0) {
			throw new ValorInvalidoException();
		}
		this.nome = nome;
		this.preco = preco;
		maiorScore = 0;
		quantJogada = 0;
		quantZerada = 0;
		this.jogabilidade = jogabilidade;	
	}
	
	/**
	 * Metodo que adiciona um novo estilo de jogo e o adiciona na lista de estilos do objeto
	 * @param estilo recebe o estilo de jogabilidade a ser adicionado na lista
	 * de estilos do jogo.
	 */
	public void addJogabilidade (Jogabilidade estilo) {
		jogabilidade.add(estilo);
	}
	
	/**
	 * Metodo que registra a jogada, verifica se o score eh o novo maiorScore e caso tenha
	 * zerado o jogo, incrementa a variavel quantZerada.
	 * @param score se for maior que o maiorScore do jogo, se torna o maiorScore
	 * @param zerou se for [true], incrementa a variavel quantZerada
	 * @throws caso o score for negativo
	 * @return 0, o x2p em si sera calculado na classe do jogo
	 */
	public int registraJogada(int score, boolean zerou) throws DadosInvalidosException {
		if(score < 0) {
			throw new ValorInvalidoException();
		}
		if(score > maiorScore) {
			maiorScore = score;
		}
		if(zerou) {
			quantZerada += 1;
		}
		quantJogada += 1;
		return 0;
	}
	
	public int x2pRecompensaVeterano() {
		int x2p = 0;
		for(Jogabilidade estilo: jogabilidade) {
			if(estilo == ONLINE) {
				x2p += 10;
			}
			if(estilo == COOPERATIVO) {
				x2p += 20;
			}
		}
		return x2p;
	}
	
	public int x2pPunicaoNoob() {
		int x2p = 0;
		for(Jogabilidade estilo: jogabilidade) {
			if(estilo == ONLINE) {
				x2p -= 10;
			}
			if(estilo == COOPERATIVO) {
				x2p -= 50;
			}
			if(estilo == COMPETITIVO) {
				x2p -= 20;
			}
		}
		return x2p;
	}
	
	public int x2pPunicaoVeterano() {
		int x2p = 0;
		for(Jogabilidade estilo: jogabilidade) {
			if(estilo == COOPERATIVO) {
				x2p -= 20;
			}
			if(estilo == COMPETITIVO) {
				x2p -= 20;
			}
		}
		return x2p;
	}
	
	public abstract int calculax2p(int score, boolean zerou);
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public int getMaiorScore() {
		return maiorScore;
	}
	
	public void setMaiorScore(int novoScore) {
		this.maiorScore = novoScore;
	}
	
	public HashSet<Jogabilidade> getJogabilidade() {
		return jogabilidade;
	}
	
	public int getQuantidadeVezesZerada() {
		return quantZerada;
	}
	
	public int getQuantidadeJogada() {
		return quantJogada;
	}
	
	/** Metodo que cria uma String com a quantidade de vezes jogadas,
	 * a quantidade de vezes zeradas e o maior Score no jogo.
	 * @return a string criada
	 */
	@Override
	public String toString() {
		String texto = String.format("==> Jogou %d vez(es)\n", quantJogada);
		texto += String.format("==> Zerou %d vez(es)\n", quantZerada);
		texto += String.format("==> Maior score: %d\n", maiorScore);
		return texto;
	}
	
	/** Hashcode que funciona a partir dos mesmo atributos do Equals.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	/** Equals que define se dois objetos do tipo Jogo sao iguais se
	 * ambos tiverem o mesmo nome e mesmo preco.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Jogo) {
			Jogo outroJogo = (Jogo) obj;
			if(nome.equals(outroJogo.getNome()) && preco == outroJogo.getPreco()) {
				return true;
			}
		}
		return false;
	}
	
}

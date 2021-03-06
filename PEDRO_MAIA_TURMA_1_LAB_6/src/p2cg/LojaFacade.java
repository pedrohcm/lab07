package p2cg;

import java.util.HashSet;

import exceptions.DadosInvalidosException;

public class LojaFacade {
	private LojaController loja;

	public LojaFacade() {
		loja = new LojaController();
	}

	public boolean criaJogo(String nome, double preco, String tipo, HashSet<Jogabilidade> jogabilidade) {
		try {
			return loja.criaJogo(nome, preco, tipo, jogabilidade);
		} catch (DadosInvalidosException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean criaUsuario(String login, String nomeUsuario, String tipo) {
		try {
			return loja.criaUsuario(login, nomeUsuario, tipo);
		} catch (DadosInvalidosException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean adicionaDinheiroUsuario(String login, double quantia) {
		try {
			return loja.adicionaDinheiroUsuario(login, quantia);
		} catch (DadosInvalidosException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean vendeJogo(String login, String nomeJogo, String tipoJogo) {
		try {
			return loja.vendeJogo(login, nomeJogo, tipoJogo);
		} catch (DadosInvalidosException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean existeUsuario(String login) {
		return loja.existeUsuario(login);
	}

	public boolean recompensar(String login, String nomeJogo, int score, boolean zerou) throws DadosInvalidosException {
		try {
			
		} catch (Exception e) {
		}
		return loja.recompensar(login, nomeJogo, score, zerou);
	}

	public boolean punir(String login, String nomeJogo, int score, boolean zerou) throws DadosInvalidosException {
		return loja.punir(login, nomeJogo, score, zerou);
	}
	
	
	
	
}


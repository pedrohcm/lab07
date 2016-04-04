package factory;

import exceptions.DadosInvalidosException;
import p2cg.Noob;
import p2cg.Usuario;

/**
 * Classe responsavel pela criacao de usuarios.
 * @author Pedro Maia
 */
public class FactoryDeUsuarios {
	
	/**
	 * Metodo que cria um objeto do tipo Usuario e o retorna.
	 * @param nomeUsuario string com o nome do usuario
	 * @param login string com o login do usuario
	 * @param tipo string com o tipo do usuario
	 * @return o objeto criado
	 * @throws DadosInvalidosException caso alguma das informacoes recebidas sejam invalidas
	 */
	public Usuario criaUsuario(String nomeUsuario, String login, String tipo) throws DadosInvalidosException {
		Usuario novoUsuario = new Usuario(nomeUsuario, login);
		return novoUsuario;	
	}
	
}

/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package testes;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import p2cg.Jogabilidade;
import p2cg.Jogo;
import p2cg.Luta;
import p2cg.Noob;
import p2cg.Plataforma;
import p2cg.RPG;
import p2cg.Usuario;

public class UsuarioTest {
	private Usuario pedro;
	private Jogo mortalKombat;
	private Jogo theWitcher;
	private Jogo raymanLegends;
	private HashSet<Jogabilidade> arrayJogabilidade = new HashSet<Jogabilidade>();
	
	@Before
	public void criaUsuarioEJogos() throws Exception {
		pedro = new Usuario("Pedro", "pedro");
		arrayJogabilidade = new HashSet<Jogabilidade>();
		arrayJogabilidade.add(Jogabilidade.COOPERATIVO);
		mortalKombat = new Luta("Mortal Kombat", 100, arrayJogabilidade);
		theWitcher = new RPG("The Witcher", 150, arrayJogabilidade);
		raymanLegends = new Plataforma("Rayman Legends", 50, arrayJogabilidade);
	}

	@Test
	public void testCompraJogo() {
		try {
			pedro.adicionaDinheiro(100);
			pedro.compraJogo(mortalKombat);
			assertEquals(10.00, pedro.getDinheiro(), 0.01);
			
			assertEquals(1, pedro.getJogos().size());
			assertEquals(1000, pedro.getX2pTotal());
			
			pedro.adicionaDinheiro(50);
			pedro.compraJogo(raymanLegends);
			assertEquals(1500, pedro.getX2pTotal());
			
			assertEquals(15.00, pedro.getDinheiro(), 0.01); // 150 - 90 - 45 (10% de desconto) = 15
			
		} catch(Exception e) {
			fail();
		}
		
		
		try {
			pedro.compraJogo(theWitcher);
			fail();
		} catch(Exception e) {
			assertEquals("Dinheiro insuficiente para a compra do jogo", e.getMessage());
		}
		
		try {
			pedro.compraJogo(raymanLegends);
			fail();
		} catch(Exception e) {
			assertEquals("Jogo ja existe na biblioteca", e.getMessage());
		}
		
	}
	
	
	@Test
	public void testUsuario() {
		try {
			Usuario nomeVazio = new Usuario("", "");
		} catch(Exception e) {
			assertEquals("Nome ou login nao podem null ou vazio", e.getMessage());
		}
		
		try {
			Usuario nomeNull = new Usuario(null, "");
		} catch(Exception e){
			assertEquals("Nome ou login nao podem ser null ou vazio", e.getMessage());
		}
		
		try {
			Usuario loginVazio = new Usuario("teste", "");
		} catch(Exception e){
			assertEquals("Nome ou login nao podem ser null ou vazio", e.getMessage());
		}
	}

	
	/*
	@Test
	public void testRegistraJogada() {
		try {
			pedro.adicionaDinheiro(250);
			
			pedro.compraJogo(theWitcher);
			pedro.compraJogo(mortalKombat);
			pedro.compraJogo(raymanLegends);
			
			pedro.registraJogada("Rayman Legends", 5000, false); // jogo de Plataforma (nao zerou) + 0
			assertEquals(2500, pedro.getX2pTotal());
			pedro.registraJogada("Rayman Legends", 7000, true); //  jogo de Plataforma (zerou) + 20
			assertEquals(2520, pedro.getX2pTotal());
			pedro.registraJogada("The Witcher", 1000, false); // jogo de RPG + 10
			assertEquals(2530, pedro.getX2pTotal());
			pedro.registraJogada("Mortal Kombat", 20000, false); // jogo de Luta (passou o maiorScore) + 20000/1000 =  + 20
			assertEquals(2550, pedro.getX2pTotal());
			pedro.registraJogada("Mortal Kombat", 10000, false); // jogo de Luta (nao passou o maiorScore) + 0
			assertEquals(2550, pedro.getX2pTotal());
			pedro.registraJogada("Mortal Kombat", 30000, false); //  jogo de Luta (passou o maiorScore) 10000/1000 = + 10
			assertEquals(2580, pedro.getX2pTotal());
			
		} catch(Exception e) {
			fail();
		}	
		
	}

	@Test
	public void testExisteJogo() {
		try {
			pedro.registraJogada("Rayman Legends", 1000, false);
			fail();
		} catch(Exception e) {
			assertEquals("Jogo nao existe na biblioteca de jogos", e.getMessage());
		}
	}

	@Test
	public void testCalculaPreco() {
		try {
			pedro.adicionaDinheiro(250);
			
			pedro.compraJogo(theWitcher);
			pedro.compraJogo(mortalKombat);
			pedro.compraJogo(raymanLegends);
			
			assertEquals(250,00, pedro.calculaPreco());
			
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testEqualsObject() {
		try {
			pedro.adicionaDinheiro(100);
			pedro.compraJogo(theWitcher);
			
			Usuario pedro2 = new Noob("Pedro");
			pedro2.adicionaDinheiro(100);
			pedro2.compraJogo(theWitcher);
			
			assertEquals(true, pedro.equals(pedro2));
			
		} catch(Exception e) {
			fail();
		}
	}
	
	*/

}

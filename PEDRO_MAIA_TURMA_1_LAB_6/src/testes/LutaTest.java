/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import p2cg.Jogo;
import p2cg.Luta;

public class LutaTest {
	private Jogo mortalKombat;
	
	@Before
	public void criaJogo() throws Exception {
		mortalKombat = new Luta("Mortal Kombat", 100);
	}
	

	@Test
	public void testConstrutor() {
		try { // nome null
			Jogo nomeInvalido = new Luta(null, 100);
		} catch (Exception e) {
			assertEquals("Nome nao pode ser null ou vazio", e.getMessage());
		}
		
		try { // nome vazio
			Jogo nomeVazio = new Luta("", 100);
		} catch(Exception e) {
			assertEquals("Nome nao pode ser null ou vazio", e.getMessage());
		}
		
		try { // preco negativo
			Jogo precoNegativo = new Luta("Preco Negativo", -1);
		} catch (Exception e) {
			assertEquals("Valor nao pode ser negativo", e.getMessage());
		}
	}
	
	@Test
	public void testRegistraJogada() {
		
		try {
			assertEquals(7, mortalKombat.registraJogada(7000, false));
			assertEquals(0, mortalKombat.registraJogada(5000, true));
			assertEquals(10, mortalKombat.registraJogada(10000, false));
			assertEquals(0, mortalKombat.registraJogada(9000, true));
			assertEquals(10000, mortalKombat.getMaiorScore());
			assertEquals(2, mortalKombat.getQuantidadeVezesZerada());
			assertEquals(4, mortalKombat.getQuantidadeJogada());
		} catch(Exception e) {
			fail();
		}
		
		try {
			assertEquals(100, mortalKombat.registraJogada(120000, false));
			assertEquals(100000, mortalKombat.getMaiorScore());
		} catch(Exception e) {
			fail();
		}
	}

}

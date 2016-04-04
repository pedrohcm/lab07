/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.DadosInvalidosException;
import p2cg.Jogo;
import p2cg.LojaController;
import p2cg.Luta;
import p2cg.Noob;
import p2cg.Plataforma;
import p2cg.RPG;
import p2cg.Usuario;

public class LojaTest {
	private LojaController loja;
	private Jogo mortalKombat;
	private Jogo theWitcher;
	private Jogo raymanLegends;
	private Usuario pedro2;
	
	@Before
	public void criaJogosEusuarios() throws DadosInvalidosException {
		loja = new LojaController();
		loja.criaUsuario("pedro", "Pedro", "noob");
		mortalKombat = new Luta("Mortal Kombat", 100);
		theWitcher = new RPG("The Witcher", 100);
		raymanLegends = new Plataforma("Rayman Legends", 50);
		pedro2 = new Noob("Pedro");
	}

	@Test
	public void testAddJogoJogo() {
		try {
			assertEquals(true, loja.addJogo(mortalKombat));
			assertEquals(true, loja.addJogo(raymanLegends));
			assertEquals(true, loja.addJogo(theWitcher));
			assertEquals(false, loja.addJogo(mortalKombat));	
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testAddJogoStringDoubleString() {
		try {
			assertEquals(true, loja.criaJogo("Fallout", 40, "rpg"));
			assertEquals(false, loja.criaJogo("", 40, "rpg"));
			assertEquals(false, loja.criaJogo("Fallout", 40, "rpg"));
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testAddUsuario() {
		try {
			assertEquals(false, loja.criaUsuario("pedro", "Pedro", "noob"));
			assertEquals(false, loja.criaUsuario("", "Pedro", "noob"));
			assertEquals(true, loja.criaUsuario("pedro2", "Pedro", "veterano"));
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testAddDinheiroUsuario() {
		try {
			assertEquals(true, loja.adicionaDinheiroUsuario("pedro", 100.00));
			assertEquals(false, loja.adicionaDinheiroUsuario("pedro3", 50.00));
			assertEquals(false, loja.adicionaDinheiroUsuario("pedro", -1.00));
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testVendeJogo() {
		try {
			assertEquals(true, loja.adicionaDinheiroUsuario("pedro", 100.00));
			assertEquals(false, loja.vendeJogo("pedro", "Mortal Kombat", "luta"));
			loja.addJogo(mortalKombat);
			loja.addUsuario("pedro2", pedro2);
			assertEquals(true, loja.adicionaDinheiroUsuario("pedro2", 100.00));
			assertEquals(true, loja.vendeJogo("pedro2", "Mortal Kombat", "luta"));
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testUpgrade() {
		try {
			loja.addJogo(mortalKombat);
			assertEquals(true, loja.adicionaDinheiroUsuario("pedro", 100.00));
			assertEquals(true, loja.vendeJogo("pedro", "Mortal Kombat", "luta"));
			assertEquals(true, loja.upgrade("pedro"));
			
			loja.criaUsuario("pedro3", "Pedro", "veterano");
			assertEquals(true, loja.adicionaDinheiroUsuario("pedro3", 100));
			assertEquals(true, loja.vendeJogo("pedro3", "Mortal Kombat", "luta"));
			
			assertEquals("veterano", loja.getListaUsuarios().get("pedro").getExperiencia());
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testEqualsObject() {
		try {
			LojaController loja2 = new LojaController();
			loja2.addJogo(mortalKombat);
			loja2.addJogo(raymanLegends);
			loja2.addUsuario("pedro", pedro2);
			
			loja.addJogo(mortalKombat);
			loja.addJogo(raymanLegends);
			loja2.addUsuario("pedro", pedro2);
			assertEquals(true, loja.equals(loja2));
			
		} catch(Exception e) {
			fail();
		}
	}

}

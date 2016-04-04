/* 115111114 - Pedro Henrique Costa Maia: LAB 6 - Turma 1 */ 

package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p2cg.Jogo;
import p2cg.Luta;
import p2cg.Veterano;
import p2cg.Plataforma;
import p2cg.RPG;
import p2cg.Usuario;

public class VeteranoTest {
	private Usuario pedro;
	private Jogo mortalKombat;
	private Jogo theWitcher;
	private Jogo raymanLegends;
	
	@Before
	public void criaUsuarioEJogos() throws Exception {
		pedro = new Veterano("Pedro");
		mortalKombat = new Luta("Mortal Kombat", 100);
		theWitcher = new RPG("The Witcher", 100);
		raymanLegends = new Plataforma("Rayman Legends", 50);
	}

	@Test
	public void testCompraJogo() {
		try {
			pedro.adicionaDinheiro(250);
			
			pedro.compraJogo(mortalKombat);
			pedro.compraJogo(raymanLegends);
			pedro.compraJogo(theWitcher);
			
			assertEquals(50,00, pedro.getDinheiro()); // 20% de desconto
			assertEquals(3750, pedro.getX2pTotal()); // 15 x2p a cada 1 real gasto
		} catch(Exception e) {
			fail();
		}
	}
	
}

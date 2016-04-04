package p2cg;

import java.util.HashMap;

public interface TipoDeUsuarioIF {
	HashMap<String, Double> compraJogo(Jogo game);
	int recompensar(Jogo game);
	int punir(Jogo game);
}

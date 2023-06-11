package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.EnemigosFueraDeRango;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

import java.util.List;

public interface Desplegable {

	void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa, int rangoAtaque, int danio) throws TerrenoNoAptoParaCaminar, TorreNoDesplegada, EnemigosFueraDeRango;

	Desplegable pasarTurno();
}

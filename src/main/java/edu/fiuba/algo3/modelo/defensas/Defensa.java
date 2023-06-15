package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.EnemigosFueraDeRango;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.TrampaArenosa;

import java.util.List;

public interface Defensa {

	public int getCostoConstruccion();


	public int getRangoAtaque();

	public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa) throws TerrenoNoAptoParaCaminar, TorreNoDesplegada, EnemigosFueraDeRango;

	public String getNombre();
}

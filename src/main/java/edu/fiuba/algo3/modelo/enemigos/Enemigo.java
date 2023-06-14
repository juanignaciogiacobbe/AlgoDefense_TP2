package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;

public interface Enemigo {
	public boolean puedeMoverseA(Parcela parcela);

	public Parcela getPasarelaActual();

	public void setPasarelaActual(ParcelaDePasarela pasarela);

	public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar;

	public void atacar(Jugador jugador) throws DefensasVacias;

	public void recibirDanio(int puntosARecibir);

	public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango;
}

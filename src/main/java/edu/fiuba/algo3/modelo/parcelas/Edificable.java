package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;

public class Edificable implements Construible {
	@Override
	public void construir(Defensa defensaAConstruir, ParcelaDeTierra parcelaActual) throws TerrenoNoAptoParaConstruir {
		parcelaActual.setDefensa(defensaAConstruir);
	}
}

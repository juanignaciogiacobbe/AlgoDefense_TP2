package edu.fiuba.algo3.modelo.parcelas;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.TrampaArenosa;

public interface Construible {
	void construir(Torre defensaAConsrtruir, Parcela parcela) throws TerrenoNoAptoParaConstruir;

	void construir(TrampaArenosa defensaAConstruir, Parcela parcela) throws TerrenoNoAptoParaConstruir;
}


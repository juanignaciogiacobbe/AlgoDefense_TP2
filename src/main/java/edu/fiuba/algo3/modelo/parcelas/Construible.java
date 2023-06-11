package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;

public interface Construible {
	void construir(Defensa defensaAConsrtruir, ParcelaDeTierra parcela) throws TerrenoNoAptoParaConstruir;
}


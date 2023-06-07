package clases;

import Excepciones.TerrenoNoAptoParaConstruir;

public interface Construible {
        void construir(Defensa defensaAConsrtruir,ParcelaDeTierra parcela) throws TerrenoNoAptoParaConstruir;
    }


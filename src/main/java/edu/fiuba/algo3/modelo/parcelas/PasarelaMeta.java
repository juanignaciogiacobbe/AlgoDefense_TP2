package edu.fiuba.algo3.modelo.parcelas;


import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.mapa.Coordenada;

import java.util.ArrayList;
import java.util.List;

public class PasarelaMeta extends ParcelaDePasarela {

    private CustomLogger logger;

    public PasarelaMeta(int abscisa, int ordenada) {
        super();
        this.construible = new NoConstruible();
        this.coordenada = new Coordenada(abscisa, ordenada);
        this.logger = CustomLogger.getInstance();
    }


}





package edu.fiuba.algo3.modelo.mapa;


import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.parcelas.*;

import java.util.ArrayList;
import java.util.List;


public class Mapa {

    private final List<Parcela> parcelas;

    private PasarelaLargada origen;

    private PasarelaMeta meta;


    public Mapa() {
        this.parcelas = new ArrayList<>();
    }

    public Mapa(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public PasarelaLargada getOrigen() {
        return origen;
    }

    public void setOrigen(PasarelaLargada origen) {
        this.origen = origen;
    }

    public PasarelaMeta getMeta() {
        return meta;
    }

    public void setMeta(PasarelaMeta meta) {
        this.meta = meta;
    }


    private ParcelaDePasarela calcularParcelaConDistanciaMinimaALaMeta(List<ParcelaDePasarela> vecinos) {
        ParcelaDePasarela parcelaFinal = null;
        int distanciaMinima = Integer.MAX_VALUE;

        for (ParcelaDePasarela vecino : vecinos) {
            int distancia = (vecino.getCoordenada()).distanciaHacia(meta.getCoordenada());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                parcelaFinal = vecino;
            }
        }

        return parcelaFinal;

    }

    public ParcelaDePasarela obtenerPasarelasEnRango(Parcela parcela, int rango) {
        List<ParcelaDePasarela> pasarelasEnRango = parcela.vecinos(this, rango);
        return this.calcularParcelaConDistanciaMinimaALaMeta(pasarelasEnRango);
    }

    public Parcela obtenerParcelaConCoordenadas(int absica, int ordenada) {
        Coordenada coordenada = new Coordenada(absica, ordenada);
        Parcela parcelaSet = null;
        for (Parcela parcela : this.getParcelas()) {
            if (parcela.getCoordenada().equals(coordenada)) {
                parcelaSet = parcela;
            }
        }
        return parcelaSet;
    }

    public void pasarTurno() {
        for (Parcela parcela : parcelas) {
            parcela.pasarTurno();
        }
    }

    public List<Enemigo> actualizarMeta(List<Enemigo> enemigos, Jugador jugador1) throws DefensasVacias {
        List<Enemigo> nuevaLista = new ArrayList<>();
        for (Enemigo enemigo : enemigos) {
                if (!enemigo.seEncuentraEn(this.meta.getCoordenada())) {
                    nuevaLista.add(enemigo);
                } else {
                    enemigo.atacar(jugador1);
                }
        }
        return nuevaLista;
    }
}







package clases;


import Excepciones.TerrenoNoAptoParaCaminar;

import java.util.ArrayList;
import java.util.List;


public class Mapa {

    private List<Parcela> parcelas;

    private PasarelaLargada origen;

    private PasarelaMeta meta;


    public Mapa() {
        this.parcelas = new ArrayList<>();
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public PasarelaLargada getOrigen() {
        return origen;
    }

    public PasarelaMeta getMeta() {
        return meta;
    }

    public void setOrigen(PasarelaLargada origen) {
        this.origen = origen;
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


}







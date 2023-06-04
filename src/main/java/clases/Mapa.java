package clases;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Mapa {

    private List<Parcela> parcelas;

    private PasarelaLargada origen;

    private PasarelaMeta meta;

    private Convertidor convertidor;

    public Mapa() {
        this.convertidor = new Convertidor();
        this.parcelas= new ArrayList<>();
        convertidor.cargarMapa(this);
        this.origen= convertidor.getOrigen();
        this.meta= convertidor.getMeta();

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

    public ParcelaDePasarela darSiguientePasarela(ParcelaDePasarela pasarela) {
        int indexPasarela = parcelas.indexOf(pasarela);
        return pasarela;
    }


    public ParcelaDePasarela hallarParcelaVecinaCorrectaADistancia(Parcela parcela, int distancia) {
        List<ParcelaDePasarela> vecinos = new ArrayList<>();
        for (Parcela parcelaMapa : parcelas) {
            if (parcelaMapa.estaADistancia(parcela.getCoordenada(), distancia) && parcelaMapa.puedeMoverseAqui()) {
                vecinos.add((ParcelaDePasarela) parcelaMapa);
            }
        }
        return (this.calcularParcelaConDistanciaMinimaALaMeta(vecinos));
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


    public ParcelaDePasarela obtenerPasarelasEnRango(ParcelaDeTierra defensa, int rango) {
        List<ParcelaDePasarela> pasarelasEnRango = new ArrayList<>();

        for (Parcela parcela : parcelas) {
            if (parcela.puedeMoverseAqui()) {
                int distancia = (parcela.getCoordenada()).distanciaHacia(defensa.getCoordenada());
                if (distancia <= rango) {
                    pasarelasEnRango.add((ParcelaDePasarela) parcela);
                }
            }
        }

        return this.calcularParcelaConDistanciaMinimaALaMeta(pasarelasEnRango);
    }

    public List<ParcelaDeTierra> obtenerDefensas() {
        List<ParcelaDeTierra> defensas = new ArrayList<>();

        for (Parcela parcela : parcelas) {
            if (parcela.puedeDefender()) {
                defensas.add((ParcelaDeTierra) parcela);
            }
        }

        return defensas;
    }

}







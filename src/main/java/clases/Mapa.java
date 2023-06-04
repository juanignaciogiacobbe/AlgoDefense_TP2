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


    private int calcularDistancia(Coordenada coordenada1, Coordenada coordenada2) {
        int x1 = coordenada1.getAbscisa();
        int y1 = coordenada1.getOrdenada();
        int x2 = coordenada2.getAbscisa();
        int y2 = coordenada2.getOrdenada();

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
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
            int distancia = calcularDistancia(vecino.getCoordenada(), meta.getCoordenada());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                parcelaFinal = vecino;
            }
        }

        //System.out.println("La coordenada vecina es"+ parcelaFinal.getCoordenada().getAbscisa() +"," + parcelaFinal.getCoordenada().getOrdenada());
        return parcelaFinal;

    }


    public ParcelaDePasarela obtenerPasarelasEnRango(ParcelaDeTierra defensa, int rango) {
        List<ParcelaDePasarela> pasarelasEnRango = new ArrayList<>();

        for (Parcela parcela : parcelas) {
            if (parcela.puedeMoverseAqui()) {
                int distancia = calcularDistancia(parcela.getCoordenada(), defensa.getCoordenada());
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







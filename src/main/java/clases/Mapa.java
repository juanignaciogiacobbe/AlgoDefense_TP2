package clases;

import java.util.ArrayList;
import java.util.List;

public class Mapa {

    private List<Parcela> parcelas;

    private ParcelaDePasarela origen;

    private ParcelaDePasarela meta;

    public Mapa() {
        this.parcelas = new ArrayList<Parcela>();
        this.origen = new PasarelaLargada(0, 0);
        this.meta = new PasarelaMeta(2, 2);
        inicializarMatrizEjemplo(origen, meta);
    }


    public ParcelaDePasarela getOrigen() {
        return origen;
    }

    public ParcelaDePasarela getMeta() {
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

    public void inicializarMatrizEjemplo(Parcela origen, Parcela meta) {
        parcelas.add(origen);
        parcelas.add(new ParcelaRocosa(2, 0));
        ParcelaDeTierra parcela = new ParcelaDeTierra(0, 1);
        parcela.setDefensa(new TorreBlanca());
        parcelas.add(parcela);
        parcelas.add(new ParcelaRocosa(0, 2));
        parcelas.add(new ParcelaRocosa(2, 1));
        parcelas.add(new PasarelaComun(1, 0));
        parcelas.add(new PasarelaComun(1, 1));
        parcelas.add(new PasarelaComun(1, 2));
        parcelas.add(meta);

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
                parcelaFinal =  vecino;
            }
        }

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

        return  this.calcularParcelaConDistanciaMinimaALaMeta(pasarelasEnRango);
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







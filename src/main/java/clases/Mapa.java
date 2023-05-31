package clases;

import java.util.ArrayList;
import java.util.List;

public class Mapa {

    private List<Parcela> parcelas;

    private Parcela origen;

    private Parcela meta;

    public Mapa() {
        this.parcelas = new ArrayList<Parcela>();
        this.origen = new PasarelaLargada(0, 0);
        this.meta = new PasarelaMeta(2, 2);
        inicializarMatrizEjemplo(origen, meta);
    }


    public Parcela getOrigen() {
        return origen;
    }

    public Parcela getMeta() {
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
        parcelas.add(new PasarelaComun(0, 2));
        parcelas.add(new ParcelaRocosa(2, 1));
        parcelas.add(new PasarelaComun(1, 0));
        parcelas.add(new PasarelaComun(1, 1));
        parcelas.add(new PasarelaComun(1, 2));
        parcelas.add(meta);

    }

    public ParcelaDePasarela hallarParcelaVecinaCorrectaADistancia(Parcela parcela, int distancia) {
        List<Parcela> vecinos = new ArrayList<>();
        for (Parcela parcelaMapa : parcelas) {
            if (parcelaMapa.estaADistancia(parcela.getCoordenada(), distancia) && parcelaMapa.puedeMoverseAqui()) {
                vecinos.add(parcelaMapa);
            }
        }
        return (this.calcularParcelaConDistanciaMinimaALaMeta(vecinos));
    }

    private ParcelaDePasarela calcularParcelaConDistanciaMinimaALaMeta(List<Parcela> vecinos)  {
        ParcelaDePasarela parcelaFinal = null;
        int distanciaMinima = Integer.MAX_VALUE;

        for (Parcela vecino : vecinos) {
            int distancia = calcularDistancia(vecino.getCoordenada(), meta.getCoordenada());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                parcelaFinal = (ParcelaDePasarela) vecino;
            }
        }

        return parcelaFinal;
    }

}







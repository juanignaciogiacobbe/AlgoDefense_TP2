package clases;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void reiniciarEnemigosPasarelas() {
        for (Parcela parcela : parcelas) {
            if (parcela.puedeMoverseAqui() && parcela.getEnemigos().size() > 0) {
                List<Enemigo> enemigosPasarela = parcela.getEnemigos();
                for (Enemigo enemigo : enemigosPasarela) {
                    enemigo.setFueMovido(false);
                }
            }
        }
    }

    public Parcela hallarParcelaVecinaCorrecta(Parcela parcela) {
        List<Parcela> vecinos = new ArrayList<>();
        Parcela parcelaFinal = null;
        int distanciaMinima = 100000;

        for (Parcela terreno : parcelas) {
            if (terreno.esVecino(parcela.getCoordenada()) && terreno.puedeMoverseAqui()) {
                vecinos.add(terreno);
            }
        }

        for (Parcela vecino : vecinos) {
            int distancia = calcularDistancia(vecino.getCoordenada(), meta.getCoordenada());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                parcelaFinal = vecino;
            }
        }
        return parcelaFinal;
    }

    public void moverEnemigos(Parcela parcela) {
        if (parcela == meta) {
            return;
        }

        List<Enemigo> enemigosParcela = new ArrayList<>(parcela.getEnemigos());
        Parcela vecino = hallarParcelaVecinaCorrecta(parcela);

        for (Enemigo enemigo : enemigosParcela) {
            if (!enemigo.isFueMovido()) {
                if (vecino != null) {
                    enemigo.mover(vecino);
                    enemigo.setFueMovido(true);
                    parcela.eliminarEnemigo(enemigo);
                }
            }
        }
        moverEnemigos(vecino);
    }

    public int obtenerCantidadEnemigosVivos() {
        int cantidadEnemigosVivos = 0;

        for (Parcela parcela : parcelas) {
            List<Enemigo> enemigosParcela = parcela.getEnemigos();

            for (Enemigo enemigo : enemigosParcela) {
                if (!enemigo.estaMuerto()) {
                    cantidadEnemigosVivos++;
                }
            }
        }

        return cantidadEnemigosVivos;
    }

    public int obtenerDanioMeta() {
        int danio = 0;
        for (Enemigo enemigo : meta.getEnemigos()) {
            danio += enemigo.getDanio();
        }
        return danio;
    }
}




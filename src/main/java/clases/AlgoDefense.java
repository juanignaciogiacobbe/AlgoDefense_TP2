package clases;

import Excepciones.NombreInvalido;
import Excepciones.SinVidaRestante;
import org.json.simple.parser.ParseException;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AlgoDefense {

    private Jugador jugador1;

    private Mapa mapa;

    private List<Enemigo> enemigos;

    private List<ParcelaDeTierra> defensas;

    public AlgoDefense(Mapa mapa, List<Enemigo> enemigos) {
        this.mapa = mapa;
        this.enemigos = enemigos;
    }
    public AlgoDefense() throws IOException, ParseException, FormatoJSONInvalidoException {
        FileReader reader = new FileReader("src/temp/mapa.json");
        ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(reader);
        this.mapa = convertidor.cargarMapa();
        this.enemigos = new ArrayList<>();
        this.defensas = new ArrayList<>();
    }

    public Mapa getMapa() {
        return this.mapa;
    }

    public void agregarJugador(String nombre) throws NombreInvalido {
        if (nombre.length() < 6) throw new NombreInvalido();

        this.jugador1 = new Jugador(nombre);
    }


    public String finDelJuego() {

        if (jugador1.getVida() <= 0 || enemigos.isEmpty()) {
            return "Computadora";
        }
        return jugador1.getNombre();

    }

    public void moverEnemigos() throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        for (Enemigo enemigo : enemigos) {
            enemigo.mover(this.mapa);
        }

        this.enemigos = this.mapa.getMeta().actualizarEnemigos(this.enemigos, jugador1);

    }


    public void agregarEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
    }




    public void cargarEnemigos(int cantTurnos) throws FileNotFoundException, FormatoJSONInvalidoException, ParseException {
        FileReader readerEnemigos = new FileReader("src/temp/enemigos.json");
        ConvertidorEnemigos convertidorEnemigos = new ConvertidorEnemigosImplementacion(readerEnemigos);
        Map<Integer, List<Enemigo>> enemigosPorRonda = convertidorEnemigos.cargarEnemigos();
        for (int i = 1; i < cantTurnos; i++) {
            for (Enemigo enemigoDefinido : enemigosPorRonda.get(i)) {
                enemigoDefinido.setPasarelaActual(mapa.getOrigen());
                enemigos.add(enemigoDefinido);
            }
        }

    }

    public void ubicarDefensa(Defensa defensa, int absica, int ordenada) {
        ParcelaDeTierra parcelaSet = null;
        Coordenada coordenadaset = new Coordenada(absica, ordenada);
        for (Parcela parcela : mapa.getParcelas()) {
            if (parcela.getCoordenada().equals(coordenadaset)) {
                parcelaSet = (ParcelaDeTierra) parcela;
            }
        }
        parcelaSet.setDefensa(defensa);
        defensas.add(parcelaSet);
    }


    public void activarDefensas() {
        for (ParcelaDeTierra parcela : defensas) {
            parcela.getDefensa().atacar(enemigos, parcela);
        }

    }
}

package clases;

import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TurnoIA implements Turno {

    Map<Integer, List<String>> enemigosTurno;

    int turnoActual;

    Mapa mapa;

    private List<Enemigo> enemigos;


    public TurnoIA(Mapa mapa) throws IOException, ParseException {
        //ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion();
        //this.enemigosTurno = convertidor.cargarEnemigos("src/temp/enemigos.json");
        this.turnoActual = 1;
        this.enemigos = new ArrayList<>();
        this.mapa = mapa;
    }

    public void ejecutarTurno() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {
        this.cargarEnemigos();
        this.moverEnemigos();
        if (this.turnoActual < 12) {
            this.turnoActual++;
        } else {
            this.turnoActual = 1;
        }
    }


    public Enemigo definirEnemigo(String nombre) {
        Enemigo enemigo = null;
        switch (nombre) {
            case "arana":
                enemigo = new Arania(mapa.getOrigen());
                break;
            case "hormiga":
                enemigo = new Hormiga(mapa.getOrigen());
                break;
            default:
                //Excepcion
                break;
        }
        return enemigo;

    }

    public void cargarEnemigos() {
        for (String valor : enemigosTurno.get(turnoActual)) {
            Enemigo enemigo = this.definirEnemigo(valor);
            enemigos.add(enemigo);
        }

    }

    public void moverEnemigos() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {
        for (Enemigo enemigo : enemigos) {
            enemigo.mover(this.mapa);
        }
    }

}

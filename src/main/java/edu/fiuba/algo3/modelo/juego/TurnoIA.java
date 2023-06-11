package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.modelo.enemigos.Arania;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.Hormiga;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TurnoIA implements Turno {

	private final List<Enemigo> enemigos;
	Map<Integer, List<String>> enemigosTurno;
	int turnoActual;
	Mapa mapa;


	public TurnoIA() throws IOException, ParseException {
		//ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion();
		//this.enemigosTurno = convertidor.cargarEnemigos("src/test/resources/enemigos.json");
		this.turnoActual = 1;
		this.enemigos = new ArrayList<>();
		this.mapa = mapa;
	}

    /*
    public void ejecutarTurno() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {
        this.cargarEnemigos();
        this.moverEnemigos();
        if (this.turnoActual < 12) {
            this.turnoActual++;
        } else {
            this.turnoActual = 1;
        }
    }

     */


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

	@Override
	public void ejecutarTurno() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {

	}

    /*
    public void moverEnemigos() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {
        for (Enemigo enemigo : enemigos) {
            enemigo.mover(this.mapa);
        }
    }

     */

}

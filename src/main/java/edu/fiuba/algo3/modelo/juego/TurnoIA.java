package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.convertidor.ConvertidorEnemigos;
import edu.fiuba.algo3.modelo.convertidor.ConvertidorEnemigosImplementacion;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.modelo.enemigos.Arania;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.Hormiga;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TurnoIA implements Turno {

	private List<Enemigo> enemigos;
	Map<Integer, List<Enemigo>> enemigosTurno;
	int turnoActual;
	Mapa mapa;

	private Jugador jugador;


	public TurnoIA(Mapa mapa, Jugador jugador) throws IOException, ParseException, FormatoJSONInvalidoException {
		FileReader readerEnemigos = new FileReader("src/resources/enemigos.json");
		ConvertidorEnemigos convertidorEnemigos = new ConvertidorEnemigosImplementacion(readerEnemigos);
		this.enemigosTurno = convertidorEnemigos.cargarEnemigos();
		this.turnoActual = 1;
		this.enemigos = new ArrayList<>();
		this.mapa = mapa;
		this.jugador= jugador;
	}


    public void ejecutarTurno() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, DefensasVacias {
		this.moverEnemigos();
		this.cargarEnemigos();
        if (this.turnoActual < 12) {
            this.turnoActual++;
        } else {
            this.turnoActual = 1;
        }
    }

	public void cargarEnemigos() {
		enemigos.addAll(enemigosTurno.get(turnoActual));
	};

	public void moverEnemigos() throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, DefensasVacias {
		for (Enemigo enemigo :enemigos) {
			enemigo.mover(this.mapa);
		}
		this.enemigos = this.mapa.getMeta().actualizarEnemigos(this.enemigos, jugador);
		this.mapa.pasarTurno();

	}



}

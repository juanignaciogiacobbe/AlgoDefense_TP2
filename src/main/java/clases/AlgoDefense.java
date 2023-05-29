package clases;

import Excepciones.NombreInvalido;

public class AlgoDefense {

    private Jugador jugador1;
    private int unidadesEnemigasVivas;
    
    public AlgoDefense(int unidadesEnemigasTotales) {
        this.unidadesEnemigasVivas = unidadesEnemigasTotales;
    }

    public void agregarJugador(String nombre) throws NombreInvalido {
        if (nombre.length() < 6) { throw new NombreInvalido();};
        jugador1 = new Jugador(nombre);
    }
    

    public void destruirUnidadEnemiga() {
        unidadesEnemigasVivas -= 1;
    }

    public String finDelJuego() {
        if (unidadesEnemigasVivas == 0) {
            return jugador1.getNombre();
        }
        return null;
    }
}
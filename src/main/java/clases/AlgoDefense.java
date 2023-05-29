package clases;

import Excepciones.NombreInvalido;

import java.util.List;



public class AlgoDefense {

    private Jugador jugador1;
    private int unidadesEnemigasVivas;
    private List<Enemigo> unidadesEnemigas;
    
    public AlgoDefense(int unidadesEnemigasTotales) {
        this.unidadesEnemigasVivas = unidadesEnemigasTotales;
    }
    public AlgoDefense(int unidadesEnemigasVivas, List<Enemigo> unidadesEnemigas) {
        this.unidadesEnemigasVivas = unidadesEnemigasVivas;
        this.unidadesEnemigas = unidadesEnemigas;
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
        } else {
            int danio = calcularDanioTotal();
            if (this.jugador1.sobreviveConDanio(danio)) {
                return jugador1.getNombre();
            }
        }
        return "Computadora";
    }

    private int calcularDanioTotal() {
        int danioTotal = 0;
        for (Enemigo unidad: unidadesEnemigas) {
            danioTotal += unidad.getDanio();
        }
        return danioTotal;
    }
}
package clases;

import Excepciones.NombreInvalido;

import java.util.List;



public class AlgoDefense {

    private Jugador jugador1;
    private List<Enemigo> unidadesEnemigas;

    public AlgoDefense(List<Enemigo> unidadesEnemigas) {
        this.unidadesEnemigas = unidadesEnemigas;
    }

    public void agregarJugador(String nombre) throws NombreInvalido {
        if (nombre.length() < 6) { throw new NombreInvalido();};
        jugador1 = new Jugador(nombre);
    }


    public void destruirUnidadEnemiga() {
        this.unidadesEnemigas.removeIf(Enemigo::estaMuerto);
    }

    public String finDelJuego() {
        if (this.unidadesEnemigasVivas() == 0) {
            return jugador1.getNombre();
        } else {
            int danio = calcularDanioTotal();
            if (this.jugador1.sobreviveConDanio(danio)) {
                return jugador1.getNombre();
            }
        }
        return "Computadora";
    }

    private int unidadesEnemigasVivas() {
        return (int) unidadesEnemigas.stream().filter(unidad -> !unidad.estaMuerto()).count();
    }

    private int calcularDanioTotal() {
        int danioTotal = 0;
        for (Enemigo unidad: unidadesEnemigas) {
            danioTotal += unidad.getDanio();
        }
        return danioTotal;
    }
}

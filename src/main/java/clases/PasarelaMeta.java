package clases;

import Excepciones.SinVidaRestante;

import java.util.ArrayList;
import java.util.List;

public class PasarelaMeta extends ParcelaDePasarela {
    public PasarelaMeta(int abscisa, int ordenada) {
        super();
        this.coordenada = new Coordenada(abscisa, ordenada);
    }

    public List<Enemigo> actualizarEnemigos(List<Enemigo> enemigos, Jugador jugador) {
        List<Enemigo> nuevaLista = new ArrayList<>();

        for (Enemigo enemigo : enemigos) {
            try {
                if (!enemigo.getPasarelaActual().getCoordenada().equals(this.getCoordenada())) {
                    nuevaLista.add(enemigo);
                } else {
                    jugador.recibirdanio(enemigo.getDanio());
                    enemigo.setPasarelaActual(this);
                }
            } catch (SinVidaRestante e) {
                // Manejar la excepción aquí (puedes imprimir un mensaje de error, tomar alguna acción, etc.)
                // ...
            }
        }

        return nuevaLista;
    }




}





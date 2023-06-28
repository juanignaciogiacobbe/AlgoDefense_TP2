package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.EnemigoFueraDeRango;
import edu.fiuba.algo3.modelo.enemigos.EnemigoNoDaniable;
import edu.fiuba.algo3.modelo.enemigos.EnemigosFueraDeRango;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.List;

public class Desplegado implements Desplegable {

    private CustomLogger logger;


    public Desplegado() {
        this.logger = CustomLogger.getInstance();
    }

    public Desplegable atacar(List<Enemigo> enemigos, Parcela parcelaDefensa, int rangoAtaque, int danio) {
        for (Enemigo enemigo : enemigos) {
            try {
                enemigo.recibirAtaque(parcelaDefensa, rangoAtaque, danio);
                logger.log("La torre ataco a una " + enemigo.getNombre() + " en la posicion (" + enemigo.getPasarelaActual().getCoordenada().getAbscisa()
                        + "," + enemigo.getPasarelaActual().getCoordenada().getAbscisa() + ")");
                Media media = new Media(new File("src/resources/sonido-disparo.mp3").toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(1); // Repetir la música indefinidamente
                mediaPlayer.play(); // Reproducir la música
                return this.pasarTurno();
            } catch (EnemigoFueraDeRango e) {
            } catch (EnemigoNoDaniable e) {
                logger.log("La torre intento atacar a una " + enemigo.getNombre() + "y no lo logro");
            }
        }

        return this.pasarTurno();

    }

    @Override
    public String toString() {
        return "D";
    }

    public Desplegable pasarTurno() {
        return this;
    }
}


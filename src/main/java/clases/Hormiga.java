package clases;

import java.util.Random;

public class Hormiga extends Enemigo{

    public Hormiga(Parcela parcelaInicial) {
        this.puntosEnergia = 2;
        this.creditos= 1;
        this.velocidad = 1;
        this.danio = 1;
        this.energia = new Vida(1);
        this.parcelaActual = parcelaInicial;
        }

    public int recibirDanio(int puntosARecibir) {
        energia.consumirPuntos(puntosARecibir);

        if (estaMuerto()) {
            return 1;
        }
        return 0;
    }

    }

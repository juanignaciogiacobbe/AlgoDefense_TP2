package clases;

public class Arania extends Enemigo {
    public Arania() {
        puntosEnergia = 2;
    }
    public void recibirDanio(int puntosARecibir) {
        puntosEnergia = puntosEnergia - puntosARecibir;
    }
}

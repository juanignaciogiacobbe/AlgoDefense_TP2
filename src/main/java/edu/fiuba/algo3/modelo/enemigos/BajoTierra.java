package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

public class BajoTierra implements Trasladable {

    private int velocidad;
    private Parcela pasarelaActual;

    private int movimientos;

    public BajoTierra(int velocidad, ParcelaDePasarela pasarela, int movimientos) {
        this.velocidad = velocidad;
        this.pasarelaActual = pasarela;
        this.movimientos = movimientos;
    }

    @Override
    public Trasladable moverse(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        ParcelaDePasarela parcelaAMover = this.pasarelaActual.mover(this.velocidad, mapa);
        this.setPasarelaActual(parcelaAMover);
        this.movimientos ++;

        return this.actualizarEstado();
    }

    public void setPasarelaActual(Parcela pasarela) {
        this.pasarelaActual = pasarela;
    }

    private Trasladable actualizarEstado() {
        Trasladable trasladableFinal;
        switch (this.movimientos) {
            case 5:
                trasladableFinal = new BajoTierra(2, (ParcelaDePasarela) this.pasarelaActual, this.movimientos);
                break;

            case 10:
                trasladableFinal = new BajoTierra(3, (ParcelaDePasarela) this.pasarelaActual, this.movimientos);
                break;

            default:
                trasladableFinal = this;
        }

        return trasladableFinal;
    }

    public Parcela getPasarelaActual() {
        return pasarelaActual;
    }
}

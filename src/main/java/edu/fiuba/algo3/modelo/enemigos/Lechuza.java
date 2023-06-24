package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

import java.util.List;

public class Lechuza implements Enemigo {

    private Atacante atacante;
    private Trasladable trasladable;

    private Daniable daniable;

    private CustomLogger logger;

    private int creditos;

    public Lechuza(ParcelaDePasarela pasarela) {
        this.atacante = new DestructorDeDefensas();
        this.trasladable = new VoladorEnL(5, pasarela);
        this.daniable = new Atacable(5);
        this.logger = CustomLogger.getInstance();
        this.creditos = 3;
    }

    @Override
    public void atacar(Jugador jugador) throws DefensasVacias {
        logger.log(this.getNombre() + "llego a la meta ,Elimino una torre");
        this.atacante.atacar(jugador);
    }
    public Parcela getPasarelaActual() {
        return trasladable.getPasarelaActual();
    }

    public void setPasarelaActual(ParcelaDePasarela pasarela) {
        this.trasladable.setPasarelaActual(pasarela);
    }

    public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        this.trasladable = trasladable.moverse(mapa);
        logger.log(this.getNombre() + " se movio a la coordenada = (" + trasladable.getPasarelaActual().getCoordenada().getAbscisa() + ","
                + trasladable.getPasarelaActual().getCoordenada().getAbscisa() + ")");

    }

    @Override
    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango, EnemigoNoDaniable {
        this.daniable.recibirAtaque(parcelaDefensa, rangoAtaque, danio, this.trasladable.getPasarelaActual());
        if (this.daniable.getVida() < 3 ){
            this.trasladable = new VoladorEnRecta(5, this.getPasarelaActual());
        }

    }

    public int recolectarCreditos() {
        return this.daniable.recolectarCreditos(this.creditos);
    }

    @Override
    public boolean seEncuentraEn(Coordenada coordenada) {
        return this.getPasarelaActual().getCoordenada().equals(coordenada);
    }

    public List<Enemigo> actualizarLista(List<Enemigo> enemigos) {
        this.daniable.actualizarLista(enemigos, this);
        return enemigos;
    }


    @Override
    public String getNombre() {
        return "Lechuza";
    }

    @Override
    public int getDanio() {
        return this.atacante.getDanio();
    }

    @Override
    public int getVida() {
        return daniable.getVida();
    }

    @Override
    public int getCreditos() {
        return 2;
    }

    @Override
    public String toString() {
        return "L";
    }

}

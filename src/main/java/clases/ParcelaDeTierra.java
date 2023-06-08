package clases;

import Excepciones.TerrenoNoAptoParDefender;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

import java.util.List;

public class ParcelaDeTierra extends Parcela {

    private Defensa defensa;

    public ParcelaDeTierra(int abscisa, int ordenada) {

        this.coordenada = new Coordenada(abscisa, ordenada);
        this.construible = new Edificable();
        this.movible = new NoDesplazable();
        this.defendible = new NoDefendible();
        this.defensa = null;

    }


    public boolean puedeMoverseAqui() {
        return false;
    }


    public void setDefensa(Defensa defensa) {
        this.defensa = defensa;
    }
    public Defensa getDefensa() {
        return defensa;
    }


    public ParcelaDePasarela mover(int distancia,Mapa mapa) throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {
        return (movible.mover(this, distancia, mapa));
    }

    public void construir(Defensa defensaAConstruir) throws TerrenoNoAptoParaConstruir {
        construible.construir(defensaAConstruir,this);
        this.defendible = new Defensora(defensaAConstruir);
    }



}

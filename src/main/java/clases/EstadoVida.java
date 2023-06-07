package clases;

import Excepciones.SinVidaRestante;

import java.util.List;

public interface EstadoVida {



    public void recibirDanio(int danioARecibir) throws SinVidaRestante;

    public void actualizarLista(List<Enemigo> lista);

    public  int recolectarCreditos(int sumaActual, int creditosEnemigo);//no nesecario por ahora

   /*
    public boolean tieneVidaIgualA(int vidaEsperada) {
        return (this.vida == vidaEsperada);
    }


    */
    public int getVida();

}

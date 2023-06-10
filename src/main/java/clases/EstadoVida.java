package clases;


import java.util.List;

public interface EstadoVida {

    public EstadoVida recibirDanio(int danioARecibir);

    public int getVida();

    int recolectarCreditos(int creditos);

}

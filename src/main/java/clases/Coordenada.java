package clases;

import java.util.Objects;

public class Coordenada {
    final int abscisa;
    final int ordenada;

    public Coordenada(int UnaAbscisa, int unaOrdenada) {
        this.abscisa = UnaAbscisa;
        this.ordenada = unaOrdenada;
    }

    public int getAbscisa() {
        return this.abscisa;
    }

    public int getOrdenada() {
        return this.ordenada;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordenada that = (Coordenada) o;
        return abscisa == that.abscisa && ordenada == that.ordenada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abscisa, ordenada);
    }
}

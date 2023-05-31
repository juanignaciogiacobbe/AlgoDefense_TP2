package clases;

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


    public Coordenada obtenerVecinoIzquierdo() {
        return (new Coordenada(this.abscisa - 1, this.ordenada));
    }

    public Coordenada obtenerVecinoDerecho() {
        return (new Coordenada(this.abscisa + 1, this.ordenada));

    }

    public Coordenada obtenerVecinoSuperior() {
        return (new Coordenada(this.abscisa, this.ordenada + 1));

    }

    public Coordenada obtenerVecinoInferior() {
        return (new Coordenada(this.abscisa, this.ordenada - 1));

    }
}

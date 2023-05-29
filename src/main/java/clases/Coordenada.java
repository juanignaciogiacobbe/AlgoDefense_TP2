package clases;

public class Coordenada {
    final double abscisa;
    final double ordenada;

    public Coordenada(double UnaAbscisa, double unaOrdenada) {
        this.abscisa = UnaAbscisa;
        this.ordenada = unaOrdenada;
    }

    public double getAbscisa() {
        return this.abscisa;
    }

    public double getOrdenada() {
        return this.ordenada;
    }
    public double calcularDistanciaA(Coordenada coordenada) {
        return Math.sqrt((this.ordenada - coordenada.getOrdenada()) * (this.ordenada - coordenada.getOrdenada()) +
                (this.abscisa - coordenada.getAbscisa()) * (this.abscisa - coordenada.getAbscisa()));
    }

}

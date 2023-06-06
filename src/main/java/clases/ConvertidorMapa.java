package clases;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface ConvertidorMapa {

    Mapa cargarMapa() throws ParseException, IOException;

    Parcela definirParcela(String terreno, int fila, int columna, Mapa mapa);
}

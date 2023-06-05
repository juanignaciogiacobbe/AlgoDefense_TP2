package clases;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ConvertidorMapa {
    Mapa cargarMapa(String path) throws IOException, ParseException;
    Parcela definirParcela(String terreno, int fila, int columna, Mapa mapa);
}
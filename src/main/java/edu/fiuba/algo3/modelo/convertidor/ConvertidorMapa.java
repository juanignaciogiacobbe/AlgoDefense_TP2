package edu.fiuba.algo3.modelo.convertidor;

import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface ConvertidorMapa {

	Mapa cargarMapa() throws ParseException, IOException, FormatoJSONInvalidoException;

	Parcela definirParcela(String terreno, int fila, int columna, Mapa mapa);
}

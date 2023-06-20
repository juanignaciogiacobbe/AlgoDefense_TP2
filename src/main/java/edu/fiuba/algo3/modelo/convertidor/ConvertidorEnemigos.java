package edu.fiuba.algo3.modelo.convertidor;

import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.Map;

public interface ConvertidorEnemigos {
	Map<Integer, List<Enemigo>> cargarEnemigos() throws ParseException, FormatoJSONInvalidoException;

	List<Enemigo> obtenerEnemigos(JSONObject jsonCantidadEnemigos);
}


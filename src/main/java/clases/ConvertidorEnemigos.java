package clases;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ConvertidorEnemigos {
    Map<Integer, List<String>> cargarEnemigos(String path) throws IOException, ParseException;
    List<String> obtenerNombresEnemigos(JSONObject jsonCantidadEnemigos);
}


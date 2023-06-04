package clases;

import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public interface ConvertidorEnemigos {
    Map<Integer, List<String>> cargarEnemigos();
    List<String> obtenerNombresEnemigos(JSONObject jsonCantidadEnemigos);
}


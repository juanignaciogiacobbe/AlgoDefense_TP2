package clases;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertidorEnemigosImplementacion implements ConvertidorEnemigos {
    @Override
    public Map<Integer, List<String>> cargarEnemigos(String path) throws IOException, ParseException {
        Map<Integer, List<String>> enemigosPorRonda = new HashMap<>(); // Diccionario para almacenar enemigos por ronda

        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(path)); // Parsear el archivo JSON

            for (Object obj : jsonArray) { // Recorrer cada objeto en el JSON
                JSONObject jsonEnemigo = (JSONObject) obj; // Obtiene bloque de codigo que tiene al turno y sus enemigos
                int turno = Integer.parseInt(jsonEnemigo.get("turno").toString()); // Obtener el número de ronda del enemigo
                JSONObject jsonCantidadEnemigos = (JSONObject) jsonEnemigo.get("enemigos"); //  Obtiene bloque de codigo que enemigos:
                List<String> enemigosRonda = obtenerNombresEnemigos(jsonCantidadEnemigos); // Obtener los nombres de los enemigos para la ronda actual
                enemigosPorRonda.put(turno, enemigosRonda);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        } catch (ParseException e) {
            throw new ParseException(-1); // Puedes ajustar el valor del offset o posición según corresponda
        }

        return enemigosPorRonda;
    }


    public List<String> obtenerNombresEnemigos(JSONObject jsonCantidadEnemigos) {
        List<String> nombresEnemigos = new ArrayList<>(); // Lista para almacenar los nombres de los enemigos de la ronda

        for (Object key : jsonCantidadEnemigos.keySet()) {
            String nombreEnemigo = (String) key; // Obtener el nombre del enemigo
            int cantidadEnemigos = Integer.parseInt(jsonCantidadEnemigos.get(nombreEnemigo).toString()); // Obtener la cantidad de enemigos

            for (int i = 0; i < cantidadEnemigos; i++) {
                nombresEnemigos.add(nombreEnemigo); // Agregar el nombre del enemigo a la lista por cada enemigo definido
            }
        }

        return nombresEnemigos; // Devolver la lista de nombres de enemigos de la ronda
    }


}

package edu.fiuba.algo3.modelo.convertidor;

import edu.fiuba.algo3.modelo.enemigos.*;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.PasarelaComun;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertidorEnemigosImplementacion implements ConvertidorEnemigos {
    private final FileReader fileReader;
    private final Parcela origen;

    public ConvertidorEnemigosImplementacion(FileReader fileReader, Parcela parcela) {
        this.fileReader = fileReader;
        this.origen = parcela;
    }

    @Override
    public Map<Integer, List<Enemigo>> cargarEnemigos() throws ParseException, FormatoJSONInvalidoException {

        Map<Integer, List<Enemigo>> enemigosPorRonda = new HashMap<>();

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(fileReader);
            this.verificarFormato(obj);
            JSONArray enemigosJson = (JSONArray) obj;

            for (Object enemigoObj : enemigosJson) {
                JSONObject enemigoJson = (JSONObject) enemigoObj;

                int turno = Integer.parseInt(enemigoJson.get("turno").toString());
                JSONObject enemigos = (JSONObject) enemigoJson.get("enemigos");

                List<Enemigo> enemigosRonda = obtenerEnemigos(enemigos);

                enemigosPorRonda.put(turno, enemigosRonda);
            }
        } catch (ParseException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enemigosPorRonda;
    }

    private void verificarFormato(Object obj) throws FormatoJSONInvalidoException, ParseException {
        if (!(obj instanceof JSONArray)) {
            throw new FormatoJSONInvalidoException("El archivo JSON debe contener una lista de objetos");
        }

        JSONArray enemigosJson = (JSONArray) obj;

        for (Object enemigoObj : enemigosJson) {
            if (!(enemigoObj instanceof JSONObject)) {
                throw new FormatoJSONInvalidoException("Cada objeto en la lista debe ser un objeto JSON");
            }

            JSONObject enemigoJson = (JSONObject) enemigoObj;

            if (!enemigoJson.containsKey("turno")) {
                throw new FormatoJSONInvalidoException("Cada objeto debe contener una clave 'turno'");
            }

            if (!enemigoJson.containsKey("enemigos")) {
                throw new FormatoJSONInvalidoException("Cada objeto debe contener una clave 'enemigos'");
            }

            Object turnoObj = enemigoJson.get("turno");
            Object enemigosObj = enemigoJson.get("enemigos");

            if (!(turnoObj instanceof Number)) {
                throw new FormatoJSONInvalidoException("El valor de 'turno' debe ser un número");
            }

            if (!(enemigosObj instanceof JSONObject)) {
                throw new FormatoJSONInvalidoException("El valor de 'enemigos' debe ser un objeto JSON");
            }
        }
    }

    public List<Enemigo> obtenerEnemigos(JSONObject jsonCantidadEnemigos) {
        List<Enemigo> enemigos = new ArrayList<>();

        for (Object key : jsonCantidadEnemigos.keySet()) {
            String nombreEnemigo = (String) key;
            int cantidadEnemigos = Integer.parseInt(jsonCantidadEnemigos.get(nombreEnemigo).toString());

            for (int i = 0; i < cantidadEnemigos; i++) {
                Enemigo enemigo = crearEnemigo(nombreEnemigo);
                enemigos.add(enemigo);
            }
        }

        return enemigos;
    }

    public Enemigo crearEnemigo(String nombreEnemigo) {
        switch (nombreEnemigo) {
            case "hormiga":
                return new Hormiga(new PasarelaComun(origen.getCoordenada().getAbscisa(), origen.getCoordenada().getOrdenada()));
            case "arana":
                return new Arania(new PasarelaComun(origen.getCoordenada().getAbscisa(), origen.getCoordenada().getOrdenada()));
            case "lechuza":
                return new Lechuza(new PasarelaComun(origen.getCoordenada().getAbscisa(), origen.getCoordenada().getOrdenada()));
            case "topo" :
                return new Topo(new PasarelaComun(origen.getCoordenada().getAbscisa(),origen.getCoordenada().getOrdenada()));
            default:
                throw new IllegalArgumentException("Unknown enemy type: " + nombreEnemigo);
        }
    }
}

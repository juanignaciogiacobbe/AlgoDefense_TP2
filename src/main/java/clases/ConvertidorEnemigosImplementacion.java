package clases;

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

	public ConvertidorEnemigosImplementacion(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	@Override
	public Map<Integer, List<Enemigo>> cargarEnemigos() throws ParseException {
		Map<Integer, List<Enemigo>> enemigosPorRonda = new HashMap<>();

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(fileReader);

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
				return new Hormiga(new PasarelaComun(0, 0));
			case "arana":
				return new Arania(new PasarelaComun(0, 0));
			default:
				throw new IllegalArgumentException("Unknown enemy type: " + nombreEnemigo);
		}
	}
}

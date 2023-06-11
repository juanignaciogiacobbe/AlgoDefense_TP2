package edu.fiuba.algo3.modelo.convertidor;

import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ConvertidorMapaImplementacion implements ConvertidorMapa {

	private final Reader reader;

	public ConvertidorMapaImplementacion(Reader reader) {
		this.reader = reader;
	}

	@Override
	public Mapa cargarMapa() throws ParseException, IOException, FormatoJSONInvalidoException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(reader);
		verificarFormato(jsonObject);

		JSONObject mapaJson = (JSONObject) jsonObject.get("Mapa");
		Mapa mapa = new Mapa();
		List<Parcela> parcelas = mapa.getParcelas();

		// Itera a través de las claves del objeto mapaJson, es decir las filas
		int j;
		for (j = 1; j <= mapaJson.size(); j++) {
			// Convierte la clave en un entero representando el número de fila
			int i = 0;
			JSONArray filaArray = (JSONArray) mapaJson.get(Integer.toString(j));

			// Itera a través de los elementos del arreglo filaArray, es decir los terrenos
			for (Object elemento : filaArray) {
				Parcela parcela = definirParcela((String) elemento, j - 1, i, mapa);
				parcelas.add(parcela); // Agrega un objeto definido a la lista de parcelas
				i++;
			}
		}
		return mapa;
	}

	private void verificarFormato(JSONObject jsonObject) throws FormatoJSONInvalidoException {
		if (!jsonObject.containsKey("Mapa")) {
			throw new FormatoJSONInvalidoException("El archivo JSON debe contener una clave 'Mapa'");
		}

		JSONObject mapaJson = (JSONObject) jsonObject.get("Mapa");
		if (mapaJson.size() < 1 || mapaJson.size() > 15) {
			throw new FormatoJSONInvalidoException("El mapa debe tener entre 1 y 15 filas");
		}

		for (int i = 1; i <= mapaJson.size(); i++) {
			String filaKey = Integer.toString(i);
			if (!mapaJson.containsKey(filaKey)) {
				throw new FormatoJSONInvalidoException("La clave 'Mapa' debe contener una clave '" + filaKey + "'");
			}

			Object filaObj = mapaJson.get(filaKey);
			if (!(filaObj instanceof JSONArray)) {
				throw new FormatoJSONInvalidoException("El valor asociado a la clave '" + filaKey + "' debe ser un arreglo");
			}

			JSONArray filaArray = (JSONArray) filaObj;
			if (filaArray.size() != 15) {
				throw new FormatoJSONInvalidoException("El arreglo asociado a la clave '" + filaKey + "' debe contener exactamente 15 elementos");
			}

			for (Object elemento : filaArray) {
				if (!(elemento instanceof String)) {
					throw new FormatoJSONInvalidoException("Los elementos del arreglo asociado a la clave '" + filaKey + "' deben ser cadenas de texto");
				}

				String terreno = (String) elemento;
				if (!terreno.equals("Rocoso") && !terreno.equals("Pasarela") && !terreno.equals("Tierra")) {
					throw new FormatoJSONInvalidoException("El terreno '" + terreno + "' no es válido. Los terrenos válidos son 'Rocoso', 'Pasarela' y 'Tierra'");
				}
			}
		}
	}


	@Override
	public Parcela definirParcela(String terreno, int fila, int columna, Mapa mapa) {
		Parcela parcela = null;
		switch (terreno) {
			case "Rocoso":
				parcela = new ParcelaRocosa(fila, columna);
				break;
			case "Pasarela":
				if (mapa.getOrigen() == null) {
					mapa.setOrigen(new PasarelaLargada(fila, columna));
				}
				parcela = new PasarelaComun(fila, columna);
				mapa.setMeta(new PasarelaMeta(fila, columna));
				break;
			case "Tierra":
				parcela = new ParcelaDeTierra(fila, columna);
				break;
			default:
				//Excepcion
				break;
		}
		return parcela;
	}
}

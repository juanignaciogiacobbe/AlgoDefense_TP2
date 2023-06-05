package clases;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class ConvertidorMapaImplementacion implements ConvertidorMapa {


    @Override
    public Mapa cargarMapa(String path) throws IOException, ParseException {
        try {
            JSONParser parser = new JSONParser();
            File file = new File(path);
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));
            JSONObject mapaJson = (JSONObject) jsonObject.get("Mapa");
            Mapa mapa = new Mapa();
            List<Parcela> parcelas = mapa.getParcelas();

            // Itera a través de las claves del objeto mapaJson, es decir las filas
            int j;
            for (j = 1; j <= 15; j++) {
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
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        } catch (ParseException e) {
            throw new ParseException(-1); // Puedes ajustar el valor del offset o posición según corresponda
        }

    }


    @Override
    public Parcela definirParcela(String terreno, int fila, int columna,Mapa mapa) {
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
                mapa.setMeta( new PasarelaMeta(fila, columna));
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

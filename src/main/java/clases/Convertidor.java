package clases;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Convertidor {

    PasarelaLargada origen;

    PasarelaMeta meta;

    public Convertidor() {
        this.origen = null;
        this.meta = null;
    }

    public void cargarMapa(Mapa mapa)  {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("src/temp/mapa.json"));
            JSONObject mapaJson = (JSONObject) jsonObject.get("Mapa");
            List<Parcela> parcelas = mapa.getParcelas();
            // Itera a través de las claves del objeto mapaJson,es decir las filas
            int j;
            for (j = 1; j <= 15; j++) {
                // Convierte la clave en un entero representando el número de fila
                int i = 0;
                JSONArray filaArray = (JSONArray) mapaJson.get(Integer.toString(j));
                // Itera a través de los elementos del arreglo filaArray,es decir los terrenos
                for (Object elemento : filaArray) {
                    Parcela parcela = definirObjeto((String) elemento, j - 1 , i);
                    parcelas.add(parcela); // Agrega un objeto definido a la lista de parcelas
                    i++;
                }
            }
        } catch (Exception ignored) {

        }

    }


    public Parcela definirObjeto(String terreno, int fila, int columna) {
        Parcela parcela = null;
        switch (terreno) {
            case "Rocoso":
                parcela = new ParcelaRocosa(fila, columna);
                break;
            case "Pasarela":

                if (origen == null) {
                    this.origen = new PasarelaLargada(fila, columna);
                    System.out.println("Crea origen");
                    System.out.println(origen.getCoordenada().getAbscisa());
                    System.out.println(origen.getCoordenada().getOrdenada());
                }
                parcela = new PasarelaComun(fila, columna);
                this.meta = new PasarelaMeta(fila, columna);



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

    public PasarelaLargada getOrigen() {
        return origen;
    }

    public PasarelaMeta getMeta() {
        return meta;
    }
}

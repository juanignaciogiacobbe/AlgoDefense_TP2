import clases.*;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class ConvertidoresTest {

    @Test
    void ConvertidorEnemigosEsCorrecto() throws IOException, ParseException {
        ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion();
        Map<Integer, List<String>> enemigosPorRonda = convertidor.cargarEnemigos("src/temp/enemigos.json");
        for (Map.Entry<Integer, List<String>> entry : enemigosPorRonda.entrySet()) {
            int turno = entry.getKey(); // Obtener el número de turno
            List<String> enemigos = entry.getValue(); // Obtener la lista de enemigos para el turno

            System.out.println("Turno: " + turno);
            System.out.println("Enemigos:");

            // Iterar sobre la lista de enemigos para el turno actual
            for (String enemigo : enemigos) {
                System.out.println(enemigo);
            }

            System.out.println(); // Imprimir una línea en blanco para separar las rondas
        }
    }
}

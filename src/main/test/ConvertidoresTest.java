import clases.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConvertidoresTest {

    @Test
    void ConvertidorEnemigosEsCorrecto() {
        ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion();
        Map<Integer, List<String>> enemigosPorRonda = convertidor.cargarEnemigos();
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

    @Test
    void test01EsCorrectaLaCantidadDeEnemigosTotales() {
        ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion();
        Map<Integer, List<String>> enemigosPorRonda = convertidor.cargarEnemigos();

        int cantidadTotalEnemigos = 0;

        // Calcular la cantidad total de enemigos
        for (List<String> enemigos : enemigosPorRonda.values()) {
            cantidadTotalEnemigos += enemigos.size();
        }

        // Realizar la aserción para verificar la cantidad total de enemigos

        Assertions.assertEquals(21, cantidadTotalEnemigos);

    }

    @Test
    void test02EsCorrectaLaCantidadDeParcelas(){
        ConvertidorMapa convertidor = new ConvertidorMapaImplementacion();
        Mapa mapa = convertidor.cargarMapa();
        Assertions.assertEquals(mapa.getParcelas().size(), 225);
    }
}

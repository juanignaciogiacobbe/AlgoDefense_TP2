import clases.ConvertidorEnemigos;
import clases.ConvertidorEnemigosImplementacion;
import clases.Enemigo;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class ConvertidoresTest {

	@Test
	void ConvertidorEnemigosEsCorrecto() throws IOException, ParseException {
		ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion();
		Map<Integer, List<Enemigo>> enemigosPorRonda = convertidor.cargarEnemigos("src/temp/enemigos.json");

		assertNotNull(enemigosPorRonda);

		// Comprobar las rondas 4 y 5
		int rondaAComprobar = 4;
		assertTrue(enemigosPorRonda.containsKey(rondaAComprobar), "Falta la ronda " + rondaAComprobar);
		List<Enemigo> enemigosRonda4 = enemigosPorRonda.get(rondaAComprobar);
		assertNotNull(enemigosRonda4, "La lista de enemigos para la ronda " + rondaAComprobar + " es nula");
		assertEquals(1, enemigosRonda4.size(), "Número incorrecto de enemigos para la ronda " + rondaAComprobar);

		rondaAComprobar = 5;
		assertTrue(enemigosPorRonda.containsKey(rondaAComprobar), "Falta la ronda " + rondaAComprobar);
		List<Enemigo> enemigosRonda5 = enemigosPorRonda.get(rondaAComprobar);
		assertNotNull(enemigosRonda5, "La lista de enemigos para la ronda " + rondaAComprobar + " es nula");
		assertEquals(2, enemigosRonda5.size(), "Número incorrecto de enemigos para la ronda " + rondaAComprobar);

		// Comprobar la longitud de las rondas
		int rondasEsperadas = 12;
		assertEquals(rondasEsperadas, enemigosPorRonda.size(), "Número incorrecto de rondas");
	}
}

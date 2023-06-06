import clases.ConvertidorEnemigos;
import clases.ConvertidorEnemigosImplementacion;
import clases.Enemigo;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;


public class ConvertidoresTest {

	@Test
	void ConvertidorEnemigosEsCorrecto() throws FileNotFoundException {
		FileReader fileReader = new FileReader("src/temp/enemigos.json");
		ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion(fileReader);
		AtomicReference<Map<Integer, List<Enemigo>>> enemigosPorRonda = new AtomicReference<>();
		assertDoesNotThrow(() -> enemigosPorRonda.set(convertidor.cargarEnemigos()));
		assertNotNull(enemigosPorRonda);

		// Comprobar las rondas 4 y 5
		int rondaAComprobar = 4;
		assertTrue(enemigosPorRonda.get().containsKey(rondaAComprobar), "Falta la ronda " + rondaAComprobar);
		List<Enemigo> enemigosRonda4 = enemigosPorRonda.get().get(rondaAComprobar);
		assertNotNull(enemigosRonda4, "La lista de enemigos para la ronda " + rondaAComprobar + " es nula");
		assertEquals(1, enemigosRonda4.size(), "Número incorrecto de enemigos para la ronda " + rondaAComprobar);

		rondaAComprobar = 5;
		assertTrue(enemigosPorRonda.get().containsKey(rondaAComprobar), "Falta la ronda " + rondaAComprobar);
		List<Enemigo> enemigosRonda5 = enemigosPorRonda.get().get(rondaAComprobar);
		assertNotNull(enemigosRonda5, "La lista de enemigos para la ronda " + rondaAComprobar + " es nula");
		assertEquals(2, enemigosRonda5.size(), "Número incorrecto de enemigos para la ronda " + rondaAComprobar);

		// Comprobar la longitud de las rondas
		int rondasEsperadas = 12;
		assertEquals(rondasEsperadas, enemigosPorRonda.get().size(), "Número incorrecto de rondas");
	}

	@Test
	void cargarEnemigos_ParseException() {
		// Arrange
		FileReader fileReaderMock = Mockito.mock(FileReader.class);
		ConvertidorEnemigosImplementacion convertidor = new ConvertidorEnemigosImplementacion(fileReaderMock);

		// Act & Assert
		assertThrows(ParseException.class, convertidor::cargarEnemigos);
	}
	@Test
	void cargarEnemigos_InvalidJson() throws IOException {
		// Arrange
		String invalidJson = "invalid json";
		FileReader fileReaderMock = Mockito.mock(FileReader.class);
		ConvertidorEnemigosImplementacion convertidor = new ConvertidorEnemigosImplementacion(fileReaderMock);

		// Mock FileReader behavior
		Mockito.when(fileReaderMock.read(Mockito.any(char[].class))).thenAnswer(invocation -> {
			char[] buffer = invocation.getArgument(0);
			System.arraycopy(invalidJson.toCharArray(), 0, buffer, 0, invalidJson.length());
			return invalidJson.length();
		});

		// Act & Assert
		assertThrows(ParseException.class, convertidor::cargarEnemigos);
	}

}

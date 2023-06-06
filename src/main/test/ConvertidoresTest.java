import clases.ConvertidorEnemigos;
import clases.ConvertidorEnemigosImplementacion;
import clases.Enemigo;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;


public class ConvertidoresTest {


	@Test
	void ConvertidorEnemigosEsCorrecto() {
		String mockedJson = "[{\"turno\": 4, \"enemigos\": {\"hormiga\": 0, \"arana\": 1}}]";
		FileReader fileReader = createFileReaderWithContent(mockedJson);

		ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion(fileReader);
		AtomicReference<Map<Integer, List<Enemigo>>> enemigosPorRonda = new AtomicReference<>();
		assertDoesNotThrow(() -> enemigosPorRonda.set(convertidor.cargarEnemigos()));
		assertNotNull(enemigosPorRonda);

		int rondaAComprobar = 4;
		assertTrue(enemigosPorRonda.get().containsKey(rondaAComprobar), "Falta la ronda " + rondaAComprobar);
		List<Enemigo> enemigosRonda4 = enemigosPorRonda.get().get(rondaAComprobar);
		assertNotNull(enemigosRonda4, "La lista de enemigos para la ronda " + rondaAComprobar + " es nula");
		assertEquals(1, enemigosRonda4.size(), "Número incorrecto de enemigos para la ronda " + rondaAComprobar);

		int rondasEsperadas = 1;
		assertEquals(rondasEsperadas, enemigosPorRonda.get().size(), "Número incorrecto de rondas");
	}

	@Test
	void cargarEnemigos_InvalidJson() {
		// Arrange
		String invalidJson = "{";
		FileReader fileReaderMock = createFileReaderWithContent(invalidJson);
		ConvertidorEnemigosImplementacion convertidor = new ConvertidorEnemigosImplementacion(fileReaderMock);

		// Act & Assert
		assertThrows(ParseException.class, convertidor::cargarEnemigos);
	}

	private FileReader createFileReaderWithContent(String content) {
		File inputFile;
		try {
			inputFile = File.createTempFile("test_input", ".json");
			inputFile.deleteOnExit();
			FileWriter fileWriter = new FileWriter(inputFile);
			fileWriter.write(content);
			fileWriter.close();
			return new FileReader(inputFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

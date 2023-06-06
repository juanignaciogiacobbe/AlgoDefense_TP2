import clases.*;
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

	@Test
	public void testVerificarFormatoDeJsonEnemigosExcepciones() {
		// Caso 1: Archivo JSON sin una lista de objetos
		String contenidoInvalido = "{\"turno\": 1, \"enemigos\": {\"hormiga\": 1, \"arana\": 0}}";
		FileReader fileReaderInvalido = createFileReaderWithContent(contenidoInvalido);

		assertThrows(FormatoJSONInvalidoException.class, () -> {
			ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion(fileReaderInvalido);
			convertidor.cargarEnemigos();
		});

		// Caso 2: Archivo JSON con objetos que no son JSON
		String contenidoInvalido2 = "[1, 2, 3]";
		FileReader fileReaderInvalido2 = createFileReaderWithContent(contenidoInvalido2);

		assertThrows(FormatoJSONInvalidoException.class, () -> {
			ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion(fileReaderInvalido2);
			convertidor.cargarEnemigos();
		});

		// Caso 3: Archivo JSON con objetos sin clave 'turno'
		String contenidoInvalido3 = "[{\"enemigos\": {\"hormiga\": 1, \"arana\": 0}}]";
		FileReader fileReaderInvalido3 = createFileReaderWithContent(contenidoInvalido3);

		assertThrows(FormatoJSONInvalidoException.class, () -> {
			ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion(fileReaderInvalido3);
			convertidor.cargarEnemigos();
		});

		// Caso 4: Archivo JSON con objetos sin clave 'enemigos'
		String contenidoInvalido4 = "[{\"turno\": 1}]";
		FileReader fileReaderInvalido4 = createFileReaderWithContent(contenidoInvalido4);

		assertThrows(FormatoJSONInvalidoException.class, () -> {
			ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion(fileReaderInvalido4);
			convertidor.cargarEnemigos();
		});

		// Caso 5: Archivo JSON con valores incorrectos para 'turno' y 'enemigos'
		String contenidoInvalido5 = "[{\"turno\": \"uno\", \"enemigos\": \"ninguno\"}]";
		FileReader fileReaderInvalido5 = createFileReaderWithContent(contenidoInvalido5);

		assertThrows(FormatoJSONInvalidoException.class, () -> {
			ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion(fileReaderInvalido5);
			convertidor.cargarEnemigos();
		});
	}

	@Test
	public void testVerificarFormatoDeJsonMapaExcepciones() {
		// Caso 1: Archivo JSON sin clave "Mapa"
		String contenidoInvalido = "{\"Turno\": 1}";
		FileReader fileReaderInvalido1 = createFileReaderWithContent(contenidoInvalido);

		assertThrows(FormatoJSONInvalidoException.class, () -> {
			ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(fileReaderInvalido1);
			convertidor.cargarMapa();
		});

		// Caso 2: Archivo JSON con clave "Mapa" pero sin claves de fila
		String contenidoInvalido2 = "{\"Mapa\": {}}";
		FileReader fileReaderInvalido2 = createFileReaderWithContent(contenidoInvalido2);

		assertThrows(FormatoJSONInvalidoException.class, () -> {
			ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(fileReaderInvalido2);
			convertidor.cargarMapa();
		});

		// Caso 3: Archivo JSON con claves de fila faltantes
		String contenidoInvalido3 = "{\"Mapa\": {\"1\": [\"Rocoso\", \"Tierra\", \"Tierra\"]}}";
		FileReader fileReaderInvalido3 = createFileReaderWithContent(contenidoInvalido3);

		assertThrows(FormatoJSONInvalidoException.class, () -> {
			ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(fileReaderInvalido3);
			convertidor.cargarMapa();
		});

		// Caso 4: Archivo JSON con arreglo de terrenos con tamaño incorrecto
		String contenidoInvalido4 = "{\"Mapa\": {\"1\": [\"Rocoso\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\"]}}";
		FileReader fileReaderInvalido4 = createFileReaderWithContent(contenidoInvalido4);

		assertThrows(FormatoJSONInvalidoException.class, () -> {
			ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(fileReaderInvalido4);
			convertidor.cargarMapa();
		});

		// Caso 5: Archivo JSON con terreno inválido
		String contenidoInvalido5 = "{\"Mapa\": {\"1\": [\"Rocoso\", \"Tierra\", \"Agua\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\", \"Tierra\"]}}";
		FileReader fileReaderInvalido5 = createFileReaderWithContent(contenidoInvalido5);

		assertThrows(FormatoJSONInvalidoException.class, () -> {
			ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(fileReaderInvalido5);
			convertidor.cargarMapa();
		});
	}


	public FileReader createFileReaderWithContent(String content) {
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

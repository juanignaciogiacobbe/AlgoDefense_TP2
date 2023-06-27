package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.*;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.Observer;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDeTierra;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class AlgoDefenseVista implements Observer, Vista {
	private Vista nextVista;
	private Scene scene;
	private List<Enemigo> enemigos;
	private Mapa mapa;
	private static final int GRID_SIZE = 15;
	private static final int CELL_SIZE = 50;

	private AlgoDefense juego;
	private Parcela ultimaParcela;


	@Override
	public void update() {
		if (scene != null) {
			this.enemigos = juego.getEnemigos();
			this.mapa = juego.getMapa();
			mostrar(scene);
		}
	}

	@Override
	public void setNextVista(Vista nextVista) {
		this.nextVista = nextVista;
		if (scene != null) {
			mostrar(scene);
		}
	}

	@Override
	public void mostrar(Scene scene) {
		this.scene = scene;

		GridPane rootPane = new GridPane();

		GridPane gridPane = createGridPane();
		configureGridConstraints(gridPane);
		displayMap(gridPane);

		rootPane.add(gridPane, 0, 0);
		VBox rightSidebar = createSidebar();
		rightSidebar.getStyleClass().add("columnaDerecha");

		rootPane.add(rightSidebar, 1, 0);
		rootPane.getStyleClass().add("mainContainer");

		scene.setRoot(rootPane);
		if (this.juego.finDelJuego() != null) {
			this.nextVista.mostrar(scene);
		}
	}

	private VBox createSidebar() {
		VBox sidebar = new VBox();

		VBox buttonBox = createButtonBox();
		HBox playerInfoBox = createPlayerInfoBox();
		VBox sidebarContent = new VBox();
		sidebarContent.getChildren().addAll(playerInfoBox, buttonBox);
		sidebarContent.setSpacing(10);

		sidebar.getChildren().add(sidebarContent);
		return sidebar;
	}

	private HBox createPlayerInfoBox() {
		HBox playerInfoBox = new HBox();
		playerInfoBox.getStyleClass().add("playerInfoBox");
		Jugador jugador = juego.getJugador();
		Label playerNameLabel = new Label("Jugador: " + jugador.getNombre());
		playerNameLabel.getStyleClass().add("infoLabel");

		Label playerCreditsLabel = new Label("Creditos restantes: " + String.valueOf(jugador.getCreditos()));
		playerCreditsLabel.getStyleClass().add("infoLabel");

		Label playerLifeLabel = new Label("Vida: " + String.valueOf(jugador.getVida()));
		playerLifeLabel.getStyleClass().add("infoLabel");

		playerInfoBox.getChildren().addAll(playerNameLabel, playerCreditsLabel, playerLifeLabel);

		return playerInfoBox;
	}

	private GridPane createGridPane() {
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		return gridPane;
	}

	private void configureGridConstraints(GridPane gridPane) {
		double cellSize = Math.min(scene.getWidth() / GRID_SIZE, scene.getHeight() / GRID_SIZE);

		for (int i = 0; i < GRID_SIZE; i++) {
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setPercentWidth(100.0 / GRID_SIZE);
			columnConstraints.setMaxWidth(cellSize);
			gridPane.getColumnConstraints().add(columnConstraints);
		}

		for (int i = 0; i < GRID_SIZE; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPercentHeight(100.0 / GRID_SIZE);
			rowConstraints.setMaxHeight(cellSize);
			gridPane.getRowConstraints().add(rowConstraints);
		}

		gridPane.getStyleClass().add("tablero");
	}

	private void displayMap(GridPane gridPane) {
		for (Parcela parcela : mapa.getParcelas()) {
			StackPane cellPane = createCellPane(parcela);
			int x = parcela.getCoordenada().getAbscisa();
			int y = parcela.getCoordenada().getOrdenada();

			gridPane.add(cellPane, x, y);
			displayEnemyInParcela(parcela, cellPane);
			displayDefenseInParcela(parcela, cellPane);
		}
	}


	private StackPane createCellPane(Parcela parcela) {
		StackPane cellPane = new StackPane();
		ImageView imageView = createParcelaImageView(parcela);
		imageView.setOnMouseClicked(
				e -> {
					if (this.ultimaParcela != null) {
						StackPane lastCellPane = findCellPane(this.ultimaParcela);
						lastCellPane.getStyleClass().remove("vistaParcelaSeleccionada");
					}
					this.ultimaParcela = parcela;
					cellPane.getStyleClass().add("vistaParcelaSeleccionada");
				});
		cellPane.getChildren().add(imageView);
		cellPane.getStyleClass().add("vistaParcela");

		// Check if this cell represents the last selected parcela
		if (parcela.equals(this.ultimaParcela)) {
			cellPane.getStyleClass().add("vistaParcelaSeleccionada");
		}

		return cellPane;
	}

	private StackPane findCellPane(Parcela parcela) {
		GridPane gridPane = (GridPane) scene.getRoot().lookup(".tablero");
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == parcela.getCoordenada().getAbscisa() &&
					GridPane.getRowIndex(node) == parcela.getCoordenada().getOrdenada()) {
				return (StackPane) node;
			}
		}
		return null;
	}

	private ImageView createParcelaImageView(Parcela parcela) {
		String imagePath = getImagePath(parcela); // Get the path to the image based on the letter of the Parcela
		Image image = new Image(imagePath, CELL_SIZE, CELL_SIZE, true, true);
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setOpacity(0.9);
		return imageView;
	}

	private String getImagePath(Parcela parcela) {
		String basePath = "src/resources/";
		return "file:" + basePath + parcela.toString() + ".png";
	}

	private String getImagePath(Defensa defensa) {
		String basePath = "src/resources/";
		return "file:" + basePath + defensa.toString() + ".png";
	}

	private String getImagePath(Enemigo enemigo) {
		String basePath = "src/resources/";
		return "file:" + basePath + enemigo.toString() + ".png";
	}

	private void displayEnemyInParcela(Parcela parcela, StackPane cellPane) {
		for (Enemigo enemigo : enemigos) {
			Parcela pasarelaActual = enemigo.getPasarelaActual();
			Coordenada coordenada = pasarelaActual.getCoordenada();//
			//Si esa parcela en el gridpane tiene un enemigo,lo coloco ahi
			if (parcela.getCoordenada().equals(coordenada)) {
				ImageView enemyImage = createEnemyImageView(enemigo);
				cellPane.getChildren().add(enemyImage);
			}
		}
	}

	private ImageView createEnemyImageView(Enemigo enemigo) {
		String imagePath = getImagePath(enemigo);
		Image image = new Image(imagePath, 42, 42, true, true);
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setOpacity(0.9);
		return imageView;
	}


	private void displayDefenseInParcela(Parcela parcela, StackPane cellPane) {
		for (ParcelaDeTierra parcela1 : juego.getDefensas()) {
			Coordenada coordenada = parcela1.getCoordenada();//
			//Si esa parcela en el gridpane tiene un enemigo,lo coloco ahi
			if (parcela.getCoordenada().equals(coordenada)) {
				ImageView defenseImage = createDefenseImageView(parcela1.getDefensa());
				cellPane.getChildren().add(defenseImage);
			}
		}
	}

	private ImageView createDefenseImageView(Defensa defensa) {
		String imagePath = getImagePath(defensa); // Get the path to the image based on the defense

		Image image = new Image(imagePath, CELL_SIZE, CELL_SIZE, true, true);
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setOpacity(0.9);
		return imageView;
	}

	public void setAlgoDefense(AlgoDefense algoDefense) {
		this.enemigos = algoDefense.getEnemigos();
		this.mapa = algoDefense.getMapa();
		this.juego = algoDefense;
		try {
			this.juego.agregarJugador("pepito");
		} catch (NombreInvalido e) {
			throw new RuntimeException(e);
		} catch (FormatoJSONInvalidoException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private VBox createButtonBox() {
		Button siguienteTurnoButton = new Button("Siguiente Turno");
		siguienteTurnoButton.getStyleClass().add("boton");
		siguienteTurnoButton.setOnAction(new SiguienteTurnoHandler(juego, this));

		Button tpButton = createDefenseButton("TP.png", new UbicarTorreHandler(juego, this, "p"));
		Button tbButton = createDefenseButton("TB.png", new UbicarTorreHandler(juego, this, "b"));
		Button sButton = createDefenseButton("S.png", new UbicarTrampaHandler(juego, this));

		VBox infoDefensa = new VBox();
		Label nombreDefensa = new Label("");
		Label creditosDefensa = new Label("");
		Label rangoDefensa = new Label("");
		Label danioDefensa = new Label("");
		Label tiempoDespliegue = new Label("");

		infoDefensa.getChildren().addAll(nombreDefensa, creditosDefensa, rangoDefensa, danioDefensa, tiempoDespliegue);

		tpButton.setOnMouseEntered(event -> new InformacionTorrePlateadaHandler());

		tbButton.setOnMouseEntered(event -> new InformacionTorreBlanca());
		sButton.setOnMouseEntered(event -> new InformacionTrampaArenosa());

		HBox defenseButtonsBox = new HBox(10, tpButton, tbButton, sButton, infoDefensa);
		VBox buttonBox = new VBox(siguienteTurnoButton, defenseButtonsBox);
		buttonBox.getStyleClass().add("contenedorBotones");
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.CENTER);
		return buttonBox;
	}


	private Button createDefenseButton(String imageName, EventHandler<ActionEvent> handler) {
		String imagePath = "file:src/resources/" + imageName;
		Image image = new Image(imagePath);
		ImageView imageView = new ImageView(image);
		Button button = new Button();
		button.setGraphic(imageView);
		button.getStyleClass().add("botonInGame");
		button.setOnAction(handler);
		return button;
	}


	public Parcela getUltimaParcela() {
		return this.ultimaParcela;
	}


}

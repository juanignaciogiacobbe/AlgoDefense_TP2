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
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import org.json.simple.parser.ParseException;

import java.io.File;
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

	private MediaPlayer mediaPlayer;


	@Override
	public void update() {
		if (scene != null) {
			this.enemigos = juego.getEnemigos();
			this.mapa = juego.getMapa();
			show(scene);
		}
	}

	@Override
	public void setNext(Vista nextVista) {
		this.nextVista = nextVista;
		if (scene != null) {
			show(scene);
		}
	}

	@Override
	public void show(Scene scene) {
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

		// Create finish game button




		scene.setRoot(rootPane);
		if (this.juego.finDelJuego() != null) {
			stopMusic();
			this.nextVista.playMusic();
			this.nextVista.show(scene);
		}
	}

	private VBox createSidebar() {
		VBox sidebar = new VBox();

		VBox buttonBox = createButtonBox();
		VBox playerInfoBox = createPlayerInfoBox();
		VBox sidebarContent = new VBox();
		sidebarContent.getChildren().addAll(playerInfoBox, buttonBox);
		sidebarContent.setSpacing(10);

		sidebar.getChildren().add(sidebarContent);
		return sidebar;
	}

	private VBox createPlayerInfoBox() {
		VBox playerInfoBox = new VBox();
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
			displaySpecialParcelas(parcela, cellPane);
			displayEnemyInParcela(parcela, cellPane);
			displayDefenseInParcela(parcela, cellPane);
		}
	}

	private void displaySpecialParcelas(Parcela parcela, StackPane cellPane) {
		Text flagEmoji = new Text();
		if (parcela.getCoordenada().equals(mapa.getOrigen().getCoordenada())) {
			ImageView imageView = createSpecialImageView("salida");
			cellPane.getChildren().add(imageView);
		} else if (parcela.getCoordenada().equals(mapa.getMeta().getCoordenada())) {
			ImageView imageView = createSpecialImageView("meta");
			cellPane.getChildren().add(imageView);
		} else {
			return;
		}
		StackPane.setAlignment(flagEmoji, Pos.CENTER);
		cellPane.getChildren().add(flagEmoji);
	}

	private ImageView createSpecialImageView(String nombre) {
		Image image = new Image("file:src/resources/"+ nombre +".png", CELL_SIZE, CELL_SIZE, true, true);
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setOpacity(0.9);
		return imageView;
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

		InformacionTorrePlateadaHandler torrePlateadaHandler = new InformacionTorrePlateadaHandler(nombreDefensa, creditosDefensa, rangoDefensa, danioDefensa, tiempoDespliegue);
		tpButton.setOnMouseEntered(torrePlateadaHandler);

		InformacionTorreBlancaHandler torreBlancaHandler = new InformacionTorreBlancaHandler(nombreDefensa, creditosDefensa, rangoDefensa, danioDefensa, tiempoDespliegue);
		tbButton.setOnMouseEntered(torreBlancaHandler);

		InformacionTrampaArenosaHandler trampaArenosaHandler = new InformacionTrampaArenosaHandler(nombreDefensa, creditosDefensa, rangoDefensa, danioDefensa, tiempoDespliegue);
		sButton.setOnMouseEntered(trampaArenosaHandler);


		HBox defenseButtonsBox = new HBox(10, tpButton, tbButton, sButton, infoDefensa);

		Button finishGameButton = new Button("Salir");
		finishGameButton.setOnAction(e -> {
			Stage stage = (Stage) finishGameButton.getScene().getWindow();
			stage.close();
		});
		finishGameButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
		VBox buttonBox = new VBox(siguienteTurnoButton, defenseButtonsBox, finishGameButton);
		VBox.setMargin(finishGameButton, new Insets(580, 0, 0, 0));
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
	public void setUltimaParcela(Parcela parcela) {
		this.ultimaParcela = parcela;
	}

	@Override
	public void playMusic() {
		Media media = new Media(new File("src/resources/sonido-juego.mp3").toURI().toString());
		this.mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Repetir la música indefinidamente
		mediaPlayer.setVolume(0.5);
		mediaPlayer.play(); // Reproducir la música
	}

	private void stopMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.dispose();
			mediaPlayer = null;
		}
	}

}

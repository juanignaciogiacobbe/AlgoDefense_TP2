package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.Observer;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.List;

public class AlgoDefenseVista implements Observer, Vista {
	private Vista nextVista;
	private Scene scene;
	private List<Enemigo> enemigos;
	private Mapa mapa;
	private static final int GRID_SIZE = 15;
	private static final int CELL_SIZE = 55;

	@Override
	public void update() {
		if (scene != null) {
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

		BorderPane rootPane = new BorderPane();
		rootPane.setPadding(new Insets(10));

		GridPane gridPane = createGridPane();
		configureGridConstraints(gridPane);
		displayMap(gridPane);

		rootPane.setCenter(gridPane);
		rootPane.setTop(new Pane());
		rootPane.setBottom(new Pane());

		scene.setRoot(rootPane);
	}

	private GridPane createGridPane() {
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setStyle("-fx-background-color: #FCFCF9;"); // Set the desired background color here
		return gridPane;
	}

	private void configureGridConstraints(GridPane gridPane) {
		for (int i = 0; i < GRID_SIZE; i++) {
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setPercentWidth(100.0 / GRID_SIZE);
			gridPane.getColumnConstraints().add(columnConstraints);
		}

		for (int i = 0; i < GRID_SIZE; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPercentHeight(100.0 / GRID_SIZE);
			gridPane.getRowConstraints().add(rowConstraints);
		}

		gridPane.setAlignment(Pos.CENTER);
	}

	private void displayMap(GridPane gridPane) {
		for (Parcela parcela : mapa.getParcelas()) {
			StackPane cellPane = createCellPane(parcela);
			int x = parcela.getCoordenada().getAbscisa();
			int y = parcela.getCoordenada().getOrdenada();

			gridPane.add(cellPane, x, y);
			displayEnemyInParcela(parcela, cellPane);
		}
	}

	private StackPane createCellPane(Parcela parcela) {
		StackPane cellPane = new StackPane();
		ImageView imageView = createParcelaImageView(parcela);
		cellPane.getChildren().add(imageView);
		return cellPane;
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
		switch (parcela.toString()) {
			case "T":
				return "file:" + basePath + "T.png";
			case "R":
				return "file:" + basePath + "R.png";
			default:
				return "file:" + basePath + "C.png";
		}
	}

	private void displayEnemyInParcela(Parcela parcela, StackPane cellPane) {
		for (Enemigo enemigo : enemigos) {
			Parcela pasarelaActual = enemigo.getPasarelaActual();
			Coordenada coordenada = pasarelaActual.getCoordenada();
			if (parcela.getCoordenada().equals(coordenada)) {
				Label enemyLabel = createEnemyLabel(enemigo);
				cellPane.getChildren().add(enemyLabel);
			}
		}
	}

	private Label createEnemyLabel(Enemigo enemigo) {
		Label enemyLabel = new Label(enemigo.toString());
		enemyLabel.setStyle("-fx-font-size: 8px; -fx-text-fill: red");
		return enemyLabel;
	}

	public void setAlgoDefense(AlgoDefense algoDefense) {
		this.enemigos = algoDefense.getEnemigos();
		this.mapa = algoDefense.getMapa();
	}
}

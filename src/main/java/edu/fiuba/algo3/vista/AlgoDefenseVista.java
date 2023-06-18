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
import javafx.scene.layout.*;

import java.util.List;

public class AlgoDefenseVista implements Observer, Vista {
	private Vista nextVista;
	private Scene scene;
	private List<Enemigo> enemigos;
	private Mapa mapa;

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
		this.configureGridConstraints(gridPane);
		this.displayMap(gridPane);

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
		return gridPane;
	}

	private void configureGridConstraints(GridPane gridPane) {
		for (int i = 0; i < 15; i++) {
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setPercentWidth(100.0 / 15);
			gridPane.getColumnConstraints().add(columnConstraints);
		}

		for (int i = 0; i < 15; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPercentHeight(100.0 / 15);
			gridPane.getRowConstraints().add(rowConstraints);
		}

		gridPane.setAlignment(Pos.CENTER);
	}


	private void displayMap(GridPane gridPane) {
		for (int i = 0; i < mapa.getParcelas().size(); i++) {
			Parcela parcela = mapa.getParcelas().get(i);
			Label parcelaLabel = createParcelaLabel(parcela);
			int x = parcela.getCoordenada().getAbscisa();
			int y = parcela.getCoordenada().getOrdenada();

			gridPane.add(parcelaLabel, x, y);
			displayEnemyInParcela(gridPane, parcela);
		}
	}

	private Label createParcelaLabel(Parcela parcela) {
		Label parcelaLabel = new Label(parcela.toString());
		parcelaLabel.setStyle("-fx-font-size: 16px; -fx-opacity: 0.3");
		return parcelaLabel;
	}

	private void displayEnemyInParcela(GridPane gridPane, Parcela parcela) {
		for (Enemigo enemigo : enemigos) {
			Parcela pasarelaActual = enemigo.getPasarelaActual();
			Coordenada coordenada = pasarelaActual.getCoordenada();
			if (parcela.getCoordenada().equals(coordenada)) {
				Label enemyLabel = createEnemyLabel(enemigo);
				int x = coordenada.getAbscisa();
				int y = coordenada.getOrdenada();

				GridPane.setConstraints(enemyLabel, x, y);
				gridPane.getChildren().add(enemyLabel);
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

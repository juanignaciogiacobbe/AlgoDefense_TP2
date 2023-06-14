package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.Observer;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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

		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		// Display map
		for (int i = 0; i < mapa.getParcelas().size(); i++) {
			Parcela parcela = mapa.getParcelas().get(i);
			Label parcelaLabel = new Label(parcela.toString());
			parcelaLabel.setStyle("-fx-font-size: 16px; -fx-opacity: 0.3");
			//parcelaLabel.setOnMouseClicked(e -> System.out.println("Clicked on parcela at position: " + parcela.getCoordenada()));
			gridPane.add(parcelaLabel, parcela.getCoordenada().getAbscisa(), parcela.getCoordenada().getOrdenada());

			// Display enemy inside parcela
			for (Enemigo enemigo : enemigos) {
				ParcelaDePasarela pasarelaActual = enemigo.getPasarelaActual();
				Coordenada coordenada = pasarelaActual.getCoordenada();
				if (parcela.getCoordenada().equals(coordenada)) {
					Label enemyLabel = new Label(enemigo.toString());
					enemyLabel.setStyle("-fx-font-size: 8px; -fx-text-fill: red");
					GridPane.setConstraints(enemyLabel, coordenada.getAbscisa(), coordenada.getOrdenada());
					gridPane.getChildren().add(enemyLabel);
				}
			}
		}


		Button finishButton = new Button("Finish Game");
		finishButton.setOnAction(e -> {
			// Handle logic to finish the game
			// ...

			if (nextVista != null) {
				nextVista.mostrar(scene);
			}
		});

		VBox vbox = new VBox(gridPane, finishButton);
		vbox.setAlignment(Pos.CENTER);
		scene.setRoot(vbox);
	}

	public void setAlgoDefense(AlgoDefense algoDefense) {
		this.enemigos = algoDefense.getEnemigos();
		this.mapa = algoDefense.getMapa();
	}
}

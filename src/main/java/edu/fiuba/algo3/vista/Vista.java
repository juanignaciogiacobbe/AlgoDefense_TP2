package edu.fiuba.algo3.vista;

import javafx.stage.Stage;

public interface Vista {
	void setNextVista(Vista nextVista);

	void mostrar(Stage stage);
}

package edu.fiuba.algo3.vista;

import javafx.scene.Scene;

public interface Vista {
	void setNextVista(Vista nextVista);

	void mostrar(Scene scene);
}

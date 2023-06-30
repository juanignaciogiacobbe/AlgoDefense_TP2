package edu.fiuba.algo3.vista;

import javafx.scene.Scene;

public interface Vista {
	void setNext(Vista nextVista);

	void show(Scene scene);

	void playMusic();
}

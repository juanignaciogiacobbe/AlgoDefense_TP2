package edu.fiuba.algo3.modelo;

public interface Observable {
	void addObserver(Observer observer);

	void notifyObservers();
}

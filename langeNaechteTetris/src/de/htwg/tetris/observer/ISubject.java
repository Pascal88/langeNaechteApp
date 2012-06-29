package de.htwg.tetris.observer;

import java.util.List;

public interface ISubject {
	public void registerObserver(IObserver o);
	public void removeObserver(IObserver o);
	public void notifyObservers(List<IObserver> observers);
}

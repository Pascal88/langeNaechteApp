package de.htwg.tetris.observer;

import java.util.List;

public interface ISubjectNewElement {
	public void registerObserver(IObserverNewElement o);
	public void removeObserver(IObserverNewElement o);
	public void notifyObservers(List<IObserverNewElement> observers);
}


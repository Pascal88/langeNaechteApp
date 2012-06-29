package de.htwg.tetris.observer;

import java.util.List;

public interface ISubjectResetGame {
	public void registerObserverReset(IObserverResetGame o);
	public void removeObserverReset(IObserverResetGame o);
	public void notifyObserversReset(List<IObserverResetGame> observers);
}

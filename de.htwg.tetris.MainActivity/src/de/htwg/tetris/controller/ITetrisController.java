package de.htwg.tetris.controller;

import de.htwg.tetris.model.IGameArray;
import de.htwg.tetris.observer.IObserverNewElement;

public interface ITetrisController extends IObserverNewElement {

	public IGameArray getGameArray();
	public void setGameArray(IGameArray gameArray);
	public void countHighscore(int i);
	public int getHighscore();
	public void setHighscore(int s);
	public ITetrisController getInstance();
	public void reStartGame();
}

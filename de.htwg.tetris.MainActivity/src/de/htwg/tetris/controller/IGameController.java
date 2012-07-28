package de.htwg.tetris.controller;

import java.util.List;

import de.htwg.tetris.model.IElement;
import de.htwg.tetris.model.IGameArray;
import de.htwg.tetris.observer.IObserverNewElement;

public interface IGameController
{
	public void resetGame();
	public void moveDown();
	public void moveUp();
	public void moveLeft();
	public void moveRight();
	
	public IElement newElement();
	public IElement getElement();
	public IElement getNextElement();
	boolean testGameOver();
	
	public IGameArray getSpielarray();
	public void setSpielarray(IGameArray spielarray);
	public IGameController getInstance();
	public List<IObserverNewElement> getObserversNewElement();
}

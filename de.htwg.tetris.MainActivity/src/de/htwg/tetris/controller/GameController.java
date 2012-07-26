package de.htwg.tetris.controller;

import java.util.List;

import de.htwg.tetris.model.GameArray;
import de.htwg.tetris.model.IElement;
import de.htwg.tetris.model.IGameArray;
import de.htwg.tetris.model.INewElement;
import de.htwg.tetris.model.NewElement;
import de.htwg.tetris.model.IQuader.states;
import de.htwg.tetris.move.IMove;
import de.htwg.tetris.move.MoveDown;
import de.htwg.tetris.move.MoveLeft;
import de.htwg.tetris.move.MoveRight;
import de.htwg.tetris.move.MoveUp;
import de.htwg.tetris.observer.IObserverNewElement;



public class GameController implements IGameController{

	public static IGameController INSTANCE = null;	
	
	private IElement element = null;
	private IMove moveDown, moveLeft, moveRight, moveUp;
	private IGameArray spielarray;

	public GameController(List<IObserverNewElement> observersNewElement) 
	{
		if(GameController.INSTANCE == null) {
			GameController.INSTANCE = this;
		}
		/* init move */
		moveDown = new MoveDown(observersNewElement);
		moveLeft = new MoveLeft();
		moveRight = new MoveRight();
		moveUp = new MoveUp();
		
		spielarray = new GameArray(); 	
	}
	
	public IGameArray getSpielarray() {
		return spielarray;
	}


	public void setSpielarray(IGameArray spielarray) {
		this.spielarray = spielarray;
	}
	
	/**
	 * verschiebt das Element nach unten
	 * 
	 * @param el
	 * 		�bergebenes Element
	 * @param m
	 * 		Richtung die das Interface Move implementiert
	 */
	public void moveDown() 
	{
		this.moveDown.move(element, spielarray);		
	}
	
	/**
	 * Dreht das Element
	 * 
	 * @param el
	 * 		�bergebenes Element
	 * @param m
	 * 		Richtung die das Interface Move implementiert
	 */
	public void moveUp() 
	{
		this.moveUp.move(element, spielarray);		
	}
	
	/**
	 * verschiebt das Element nach Links
	 * 
	 * @param el
	 * 		�bergebenes Element
	 * @param m
	 * 		Richtung die das Interface Move implementiert
	 */
	public void moveLeft() 
	{
		this.moveLeft.move(element, spielarray);		
	}
	
	/**
	 * verschiebt das Element nach Rechts
	 * 
	 * @param el
	 * 		�bergebenes Element
	 * @param m
	 * 		Richtung die das Interface Move implementiert
	 */
	public void moveRight() 
	{
		this.moveRight.move(element, spielarray);		
	}
	
	public boolean testGameOver()
	{
		return spielarray.arrayStateOfElementPosition(element, states.TAKEN);	
	}
	
	public IElement newElement()
	{
		INewElement newEle = new NewElement();
		element = newEle.newEl();
		this.spielarray.elementMergeArray(this.getElement());
		return element;
	}
	

	public IElement getElement()
	{
		return element;
	}

	@Override
	public void resetGame() 
	{
		spielarray.resetGame();	
		this.newElement();
	}

	public IGameController getInstance() {
		return GameController.INSTANCE;
	}
}

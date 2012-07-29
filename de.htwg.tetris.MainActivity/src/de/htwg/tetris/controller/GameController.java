package de.htwg.tetris.controller;

import java.util.List;
import de.htwg.tetris.model.GameArray;
import de.htwg.tetris.model.IElement;
import de.htwg.tetris.model.IGameArray;
import de.htwg.tetris.model.INewElement;
import de.htwg.tetris.model.NewElement;
import de.htwg.tetris.move.IMove;
import de.htwg.tetris.move.MoveDown;
import de.htwg.tetris.move.MoveLeft;
import de.htwg.tetris.move.MoveRight;
import de.htwg.tetris.move.MoveUp;
import de.htwg.tetris.observer.IObserverNewElement;



public class GameController implements IGameController{

	public static IGameController INSTANCE = null;	
	
	private IElement element = null;
	private IElement nextElement = null;
	private IMove moveDown, moveLeft, moveRight, moveUp;
	private IGameArray gameArray;
	private List<IObserverNewElement> observersNewElement;

	public GameController(List<IObserverNewElement> observersNewElement) 
	{
		if(GameController.INSTANCE == null) {
			GameController.INSTANCE = this;
		}
		
		this.observersNewElement = observersNewElement;
		/* init move */
		moveDown = new MoveDown(observersNewElement);
		moveLeft = new MoveLeft();
		moveRight = new MoveRight();
		moveUp = new MoveUp();
		
		gameArray = new GameArray(); 	
	}
	
	public IGameArray getGameArray() {
		return gameArray;
	}


	public void setGameArray(IGameArray gameArray) {
		this.gameArray = gameArray;
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
		moveDown.move(element, gameArray);		
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
		moveUp.move(element, gameArray);		
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
		moveLeft.move(element, gameArray);		
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
		moveRight.move(element, gameArray);		
	}
	
	public boolean testGameOver()
	{
		return gameArray.isGameOver();
	}
	
	public IElement newElement()
	{
		INewElement newEle = new NewElement();
		if(nextElement == null) nextElement = newEle.newEl();
		element = nextElement;
		nextElement = newEle.newEl();
		gameArray.elementMergeArray(getElement());
		return element;
	}
	

	public IElement getElement()
	{
		return element;
	}

	@Override
	public void resetGame() 
	{
		gameArray.resetGame();	
		newElement();
	}

	public IGameController getInstance() {
		return GameController.INSTANCE;
	}

	public List<IObserverNewElement> getObserversNewElement() {
		return observersNewElement;
	}

	public IElement getNextElement() {
		return nextElement;
	}
}

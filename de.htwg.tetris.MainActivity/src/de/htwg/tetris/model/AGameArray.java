package de.htwg.tetris.model;

import java.util.ArrayList;
import java.util.List;
import de.htwg.tetris.model.IQuader.states;
import de.htwg.tetris.observer.ASubject;
import de.htwg.tetris.observer.IObserver;
import de.htwg.tetris.observer.IObserverResetGame;

public abstract class AGameArray extends ASubject implements IGameArray{
	
	private static final long serialVersionUID = 99977945366647965L;
	private IQuader[][] game = null;
	public final static int WIDTH = 10; 	// number of elements in the width 
	public final static int HEIGHT = 20; // number of elements in the height
	protected List<IObserverResetGame> observerResetGame;

	public AGameArray() 
	{
		game = new Quader[WIDTH][HEIGHT]; 	// init Array
		for (int i = 0; i < WIDTH; i++) 		// init Elements in the Array
		{
			for (int j = 0; j < HEIGHT; j++) 
			{
				game[i][j] = new Quader();
			}
		}
		observers = new ArrayList<IObserver>();
		observerResetGame = new ArrayList<IObserverResetGame>();
		notifyObservers(observers);	
	}
	
	public void resetGame()
	{
		for (int i = 0; i < WIDTH; i++) // init Elements in the Array
		{
			for (int j = 0; j < HEIGHT; j++) {
				game[i][j].setState(states.FREE);
			}
		}
		notifyObservers(observers);
		notifyObserversReset(observerResetGame);
	}

	public IQuader[][] getGame() {
		return game;
	}
	
	
	
	/**
	 * check if the spielarray has the passed state on the element position
	 * 
	 * @param el
	 * 		element that specifies the positions to be checked 
	 * @param z
	 * 		state to check
	 * @return true 
	 * 		if the spielarray has the passed state 
	 */
	public abstract boolean arrayStateOfElementPosition(IElement el, states z);
	
	
	/**
	 * check if the element is out of the spielarray
	 * 
	 * @param el
	 * 		element that specifies the positions to be checked 
	 * @return true 
	 * 		if the element is in the spielarray
	 */
	public abstract boolean arrayElementInArray(IElement el);
	
	
	/**
	 * check if the element is LEFT in the array
	 * @param el
	 * 		element that specifies the positions to be checked 
	 * @return true 
	 * 		if the element is in the array
	 */
	public abstract boolean arrayElementInLeft(IElement el);
	
	/**
	 * check if the element is RIGHT in the array
	 * @param el
	 * 		element that specifies the positions to be checked 
	 * @return true 
	 * 		if the element is in the array
	 */
	public abstract boolean arrayElementInRight(IElement el);

	/**
	 * check if the element is DOWN in the array
	 * @param el
	 * 		element that specifies the positions to be checked 
	 * @return true 
	 * 		if the element is in the array
	 */
	public abstract boolean arrayElementInDown(IElement el);
	
	public abstract void elementMergeArray(IElement el);
	
	public void setStateColor(int x, int y, states z, ITetrisColor c)
	{
		game[x][y].setState(z);
		game[x][y].setColor(c);
		
	}
	/*
	 * State
	 */
	public states getState(int x, int y) 
	{
		states ret;
		if ((x >= 0) & (x < WIDTH) & (y >= 0) & (y < HEIGHT)) {
			ret = game[x][y].getState();
		} else {
			ret = states.TAKEN;
		}
		return ret;
	}
	
	public void setState(int x, int y, states z) {
		game[x][y].setState(z);
		notifyObservers(observers);
	}

	
	/*
	 * TetrisColor
	 */
	public void setColor(int x, int y, ITetrisColor c) {
		game[x][y].setColor(c);
		notifyObservers(observers);
	}
	public ITetrisColor getColor(int x, int y) {
		return game[x][y].getColor();
	}

	@Override
	public void registerObserverReset(IObserverResetGame o) {
		observerResetGame.add(o);		
	}

	@Override
	public void removeObserverReset(IObserverResetGame o) {
		int i = observerResetGame.indexOf(o);
		if(i>=0)
		{
			observerResetGame.remove(i);
		}		
	}

	@Override
	public void notifyObserversReset(List<IObserverResetGame> observersResetGame) {
		for (int i=0; i<observersResetGame.size();i++)
		{
			IObserverResetGame observer = observersResetGame.get(i);
			observer.update();
		}		
	}

	public void setGame(IQuader[][] game) {
		this.game = game;
	}
	
	public boolean isGameOver(){
		if(this.game[5][2].getState() == states.TAKEN){
			return true;
		}
		return false;
	}
}

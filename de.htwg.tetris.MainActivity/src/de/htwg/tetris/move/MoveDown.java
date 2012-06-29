package de.htwg.tetris.move;

import java.util.List;

import de.htwg.tetris.gui.swing.IGameField;
import de.htwg.tetris.model.IElement;
import de.htwg.tetris.model.IGameArray;
import de.htwg.tetris.model.IQuader.states;
import de.htwg.tetris.model.ITetrisColor;
import de.htwg.tetris.observer.ASubjectNewElement;
import de.htwg.tetris.observer.IObserverNewElement;

public class MoveDown extends ASubjectNewElement implements IMove
{
	private IGameArray gameArray;
	
	public MoveDown(List<IObserverNewElement> observersNewElement)
	{
		this.observersNewElement = observersNewElement;
	}
	
	
	@Override
	public void move(IElement el, IGameArray gameArray) {
		this.gameArray = gameArray;
		if (moveTest(el, gameArray)) // move if it is possible
		{
			el.setY(el.getY() + 1);
			gameArray.elementMergeArray(el);		
		}	
		else	// element has reached the bottom
		{
			elementMergeArrayBottom(el);
			countFullLine = fullLine();		//return sum delete lines
			notifyObservers(observersNewElement);
		}
	}

	@Override
	public boolean moveTest(IElement el, IGameArray gameArray) 
	{
		boolean ret = true;
		int y = el.getY();
		el.setY(y + 1);

		// check border
		if (!(gameArray.arrayElementInDown(el)))
		{
			ret = false;
		}	
		// check if there is another element
		if (gameArray.arrayStateOfElementPosition(el, states.TAKEN))
		{
			el.setState(false);
			ret = false;
		}
		el.setY(y);
		return ret;
	}
	
	

	
	public void elementMergeArrayBottom(IElement el) 
	{
		setGameArrayElementToState(el, states.TAKEN);
	}
	
	
	private void setGameArrayElementToState(IElement el, states z) 
	{
		int x = el.getX();
		int y = el.getY();
		
		gameArray.setStateColor(x, y, z, el.getColor());
		gameArray.setStateColor(
				(x + el.getQ2().getX()), (y + el.getQ2().getY()), 
				z, el.getColor());
		gameArray.setStateColor(
				(x + el.getQ3().getX()), (y + el.getQ3().getY()), 
				z, el.getColor());
		gameArray.setStateColor(
				(x + el.getQ4().getX()), (y + el.getQ4().getY()), 
				z, el.getColor());
	}
	
	
	
	private void deleteFullLine(int j) {
		for (int y = j; y > 0; y--) 
		{
			for (int x = 0; x < IGameField.WIDTH; x++) {
				gameArray.setState(x, y, gameArray.getState(x, y-1));
				ITetrisColor c = gameArray.getColor(x, y-1);
				gameArray.setColor(x, y, c);
			}
		}
	}

	public int fullLine() 
	{
		int i = 0;
		for (int y = 0; y < IGameField.HEIGHT; y++) 
		{
			int count = 0;
			for (int x = 0; x < IGameField.WIDTH; x++) {
				if (gameArray.getState(x, y) != states.TAKEN) {
					break; // break, because row can not be full
				}
				count++;
			}
			if (count == 10) 
			{
				i++;
				deleteFullLine(y);
			}
		}
		return i;
	}
}

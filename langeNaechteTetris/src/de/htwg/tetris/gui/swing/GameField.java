package de.htwg.tetris.gui.swing;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import de.htwg.tetris.controller.GameController;
import de.htwg.tetris.gui.swing.IGameField;
import de.htwg.tetris.model.IGameArray;
import de.htwg.tetris.model.ITetrisColor;
import de.htwg.tetris.model.IQuader.states;
import de.htwg.tetris.observer.IObserver;

public class GameField extends JPanel implements IObserver, IGameField{

	private static final long serialVersionUID = 1L;
	private final static int EL = 30; 		// Element width and height
	private IGameArray gameArray = null;


	public GameField() 
	{
		this.gameArray = GameController.INSTANCE.getSpielarray();
		this.gameArray.registerObserver(this);
	}
	
	public void update() 
	{
		repaint();		
	}	
	
	public void resetGameField() 
	{
		gameArray.resetGame();
	}

	/**
	 *  paint the Array
	 */
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		for (int i = 0; i < IGameField.WIDTH; i++) {
			for (int j = 0; j < IGameField.HEIGHT; j++) {
				if (gameArray.getState(i, j) != states.FREE) {
					ITetrisColor c = gameArray.getColor(i,j);
					Color f = new Color(c.getR(), c.getG(), c.getB());
					g.setColor(f);
					g.fillRect(EL * i, EL * j, EL, EL);
				}
			}
		}
	}
}
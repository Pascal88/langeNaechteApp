package de.htwg.tetris.gui.swing;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.htwg.tetris.controller.GameController;
import de.htwg.tetris.controller.IMechanikController;
import de.htwg.tetris.controller.IGameController;
import de.htwg.tetris.controller.MechanikController;
import de.htwg.tetris.model.IGameArray;



public class NewGameListener implements ActionListener 
{
	private IGameArray gameArray = null;
	private IGameController gameController = null;
	private IMechanikController mechanikController = null;
	
	public NewGameListener() {
		this.gameArray = GameController.INSTANCE.getSpielarray();
		this.gameController = GameController.INSTANCE;
		this.mechanikController = MechanikController.INSTANCE;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		newGame();
	}
	
	private void newGame()
	{
		mechanikController.stopMechanic();
		gameController.resetGame();
		gameController.newElement();
		gameArray.elementMergeArray(gameController.getElement());

		mechanikController.newMechanik();
		
	}
}

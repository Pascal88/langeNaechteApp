package de.htwg.tetris.controller;

import de.htwg.tetris.gui.swing.Gui;
import de.htwg.tetris.model.IGameArray;

public class TetrisController implements ITetrisController {
	
	public static ITetrisController INSTANCE = null;

	private IGameArray spielarray;
	private Gui frame;
	private int highscore = 0;
	
	private IGameController gameController;
	private IMechanikController mechanikController;
	
	public TetrisController(IGameController gameController, IMechanikController mechanikController) {
		
		if(TetrisController.INSTANCE == null) {
			TetrisController.INSTANCE = this;
		}
		
		this.gameController = gameController;
		this.mechanikController = mechanikController;
		this.spielarray = gameController.getSpielarray(); 	
		this.spielarray.registerObserverReset(this);
		
		
		frame = new Gui();
		
	}

	public IGameArray getSpielarray() {
		return spielarray;
	}

	public void setSpielarray(IGameArray spielarray) {
		this.spielarray = spielarray;
	}
	
	public void update(int countFullLine) {
		gameController.newElement();
		countHighscore(countFullLine);
		if (gameController.testGameOver()) {
			mechanikController.stopMechanic();
			gameController.resetGame();
			this.mechanikController.newMechanik();
		}
	}
	
	public void countHighscore(int i)
	{
		highscore += (i*100);
		if (i>0) {
			this.frame.setTextFieldValue(highscore);
		}
	}

	public void update() {
		
		//this.frame.resetTextField();		
	}

	public Gui getFrame() {
		return frame;
	}	
	
	public int getHighscore(){
		return highscore;
	}
	
	public void setHighscore(int s){
		this.highscore = s;
		this.frame.setTextFieldValue(highscore);
	}

	@Override
	public ITetrisController getInstance() {
		return TetrisController.INSTANCE;
	}
}

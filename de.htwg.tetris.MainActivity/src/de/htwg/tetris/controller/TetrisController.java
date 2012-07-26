package de.htwg.tetris.controller;

import de.htwg.tetris.model.IGameArray;

public class TetrisController implements ITetrisController {
	
	public static ITetrisController INSTANCE = null;

	private IGameArray spielarray;
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
		//this.activity.updateGuiScore(highscore);
	}

	public void update() {
		
		//this.frame.resetTextField();		
	}
	
	public int getHighscore(){//TODO call this from game
		return highscore;
	}
	
	public void setHighscore(int s){
		this.highscore = s;
	}

	@Override
	public ITetrisController getInstance() {
		return TetrisController.INSTANCE;
	}
}

package de.htwg.tetris.controller;

import de.htwg.tetris.model.IGameArray;

public class TetrisController implements ITetrisController {
	
	public static ITetrisController INSTANCE = null;

	private IGameArray gameArray;
	private int highscore = 0;
	
	private IGameController gameController;
	private IMechanikController mechanikController;
	
	public TetrisController(IGameController gameController, IMechanikController mechanikController) {
		
		if(TetrisController.INSTANCE == null) {
			TetrisController.INSTANCE = this;
		}
		
		this.gameController = gameController;
		this.mechanikController = mechanikController;
		gameArray = gameController.getGameArray(); 	
	}

	public IGameArray getGameArray() {
		return gameArray;
	}

	public void setGameArray(IGameArray gameArray) {
		this.gameArray = gameArray;
	}
	
	public void update(int countFullLine) {
		countHighscore(countFullLine);
		if (gameController.testGameOver()) {
			mechanikController.stopMechanic();
		}
		gameController.newElement();
	}
	
	public void countHighscore(int i)
	{
		highscore += (i*100);
	}
	
	public int getHighscore(){
		return highscore;
	}
	
	public void setHighscore(int s){
		highscore = s;
	}

	public ITetrisController getInstance() {
		return TetrisController.INSTANCE;
	}
	
	public void reStartGame(){
		if(mechanikController.isMechanikAlive()){//if a Gameloop is running
			mechanikController.stopMechanic();//stop it
		}
		gameController.resetGame();//clear all models
		mechanikController.newMechanik();//start new game loop
	}
}

package de.htwg.tetris.model;

public class SaveGame implements ISaveGame {
	
	public String name = null;
	public IQuader[][] game = null;
	public int highscore = 0;

	public SaveGame(String s, IQuader[][] game, int score) {
		this.name = s;
		this.game = game;
		this.highscore = score;
	}

	public SaveGame() {

	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public IQuader[][] getGame() {
		return game;
	}

	public void setGame(IQuader[][] game) {
		this.game = game;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}	
}

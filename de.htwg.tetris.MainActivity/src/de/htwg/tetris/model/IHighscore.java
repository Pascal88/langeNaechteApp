package de.htwg.tetris.model;

public interface IHighscore {
	public void storeHighScore(String userName, int score);
	public HighScoreBean[] loadHighScore(int count);
}

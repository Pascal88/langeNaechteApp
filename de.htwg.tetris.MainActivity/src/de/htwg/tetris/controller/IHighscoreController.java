package de.htwg.tetris.controller;

import de.htwg.tetris.model.HighScoreBean;

public interface IHighscoreController {
	public void saveHighScore(String username, int score);
	public HighScoreBean[] loadHighScore(int count);
	public IHighscoreController getInstance();
}

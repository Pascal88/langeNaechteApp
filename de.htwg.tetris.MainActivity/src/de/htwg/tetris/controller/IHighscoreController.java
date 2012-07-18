package de.htwg.tetris.controller;

import de.htwg.tetris.model.HighScoreBean;

public interface IHighscoreController {
	//true wenn alles geklappt hat false wenn etwas schiefging
	public Boolean saveHighScore(String username, int score);
	//HighScoreBean[] wenn alles ok null falls was schief ging
	public HighScoreBean[] loadHighScore(int count);
	public IHighscoreController getInstance();
}

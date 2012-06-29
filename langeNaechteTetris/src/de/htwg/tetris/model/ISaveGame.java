package de.htwg.tetris.model;

public interface ISaveGame {
	public String getName();
	public void setName(String name);
	public IQuader[][] getGame();
	public void setGame(IQuader[][] game);
	public int getHighscore();
	public void setHighscore(int highscore);
}

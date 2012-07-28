package de.htwg.tetris.model;

import java.io.Serializable;

public interface IElement extends Serializable{
	public void turn();
	public void setQuaders(ITetrisPoint[] p);
	public void setTurnStatus(int status);
	public int getTurnStatus();
	public Boolean getState();
	public void setState(Boolean state);
	public ITetrisColor getColor();
	public void setX(int x);
	public int getX();
	public int getY();
	public void setY(int y);
	public IQuader getQ2();
	public IQuader getQ3();
	public IQuader getQ4();
	public void setQ1(IQuader q1);
	public IQuader getQ1(); 
	public TetrisPoint[] getInitialView();
}

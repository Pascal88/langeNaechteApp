package de.htwg.tetris.model;

import java.io.Serializable;

public interface IQuader extends Serializable{
	public enum states {
		FREE, ELEMENT, TAKEN
	}
	
	public void setColor(ITetrisColor f);
	public ITetrisColor getColor();
	public void setState(states state);
	public states getState();
	
	public int getX();
	public void setX(int x);
	public void setXY(double d, double e);
	public int getY();
	public void setY(int y);
}

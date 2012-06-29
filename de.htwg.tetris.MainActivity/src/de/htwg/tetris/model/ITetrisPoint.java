package de.htwg.tetris.model;

import java.io.Serializable;

public interface ITetrisPoint extends Serializable{
	public int getX();
	public void setX( int x);
	public int getY();
	public void setY(int y);
}

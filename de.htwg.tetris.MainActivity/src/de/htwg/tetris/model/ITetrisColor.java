package de.htwg.tetris.model;

import java.io.Serializable;

public interface ITetrisColor extends Serializable {

	public int getR();
	public void setR(int r);
	public int getG();
	public void setG(int g);
	public int getB();
	public void setB(int b);
	public int getA();
	public void setA(int a);
}

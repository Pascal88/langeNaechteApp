package de.htwg.tetris.model;

public class TetrisColor implements ITetrisColor{

	private static final long serialVersionUID = 798799433476336963L;
	int r,g,b,a;
	
	public TetrisColor() { }
	public TetrisColor(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	public TetrisColor(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
}

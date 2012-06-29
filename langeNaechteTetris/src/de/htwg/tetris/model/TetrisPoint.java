package de.htwg.tetris.model;

public class TetrisPoint implements ITetrisPoint {

	private static final long serialVersionUID = -6400122771736016690L;
	int x,y;
	
	public TetrisPoint () { }
	public TetrisPoint (int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int getX() {
		return x;
	}
	@Override
	public void setX(int x) {
		this.x = x;		
	}
	@Override
	public int getY() {
		return y;
	}
	@Override
	public void setY(int y) {
		this.y = y;
	}
}

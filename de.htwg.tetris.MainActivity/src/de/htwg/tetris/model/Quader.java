package de.htwg.tetris.model;

public class Quader extends AQuader{
	
	private static final long serialVersionUID = -4713173123647432535L;
	/**
	 * 		zustand und Farbe sind in Array_Quader definiert
	 * 		zustand kann: enum zust{FREI, ELEMENT, BELEGT} sein
	 */
	private int x = 0;
	private int y = 0;
	
	public Quader(){		
	}
	
	public Quader(int x, int y){
		setX(x);
		setY(y);	
	}
	
	public Quader(ITetrisColor f) {
		super(f);
	}
	
	//Getter Setter
	
	public int getX() {
		return x;
	}

	public final void setX(int x) {
		this.x = x;
	}
	public void setXY(double d, double e) {
		this.x = (int) d;
		this.y = (int) e;
	}

	public int getY() {
		return y;
	}

	public final void setY(int y) {
		this.y = y;
	}
}

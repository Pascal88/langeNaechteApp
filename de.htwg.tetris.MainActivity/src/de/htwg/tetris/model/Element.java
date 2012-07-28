package de.htwg.tetris.model;

public abstract class Element implements IElement
{
	private static final long serialVersionUID = -5851163025449609694L;
	/*
	 * Private
	 */
	private int x = 5;
	private int y = 1;
	private int turnState = 1;
	private final int numOfQuaders = 4;
	private IQuader[] q = new Quader[numOfQuaders];
	private Boolean state = true; // move if true
	
	/*
	 * Protected
	 */	
	protected TetrisPoint turn[][]; 
	protected TetrisColor colorElement;
	protected char name;
	
	/*
	 * Constructor
	 */
	public Element() {
		for(int i = 0; i < numOfQuaders; i++) {
			q[i] = new Quader();
		}
	}
	
	/*
	 * Methods
	 */
	public void turn() 
	{
		if(turnState == 4) {
			turnState = 0;
		}
		setTurnStatus(turnState + 1);		
	}
	
	/*
	 * Getters & Setters
	 */
	
	public void setQuaders(ITetrisPoint[] p) {
		for(int i = 0; i < 4; i++) {
			if(p[i] == null) {
				continue;
			}
			q[i].setXY(p[i].getX(), p[i].getY());
		}	
	}
	
	public void setTurnStatus(int status) {

		try{
			this.turnState = status;
			this.setQuaders(turn[status-1]);
		} catch (ArrayIndexOutOfBoundsException e){	
		}
		
	}
	
	public int getTurnStatus() {
		return turnState;
	}	
	
	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public TetrisColor getColor() {
		return colorElement;
	}

	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/*
	 * get set Quader
	 */

	public IQuader getQ2() {
		return q[1];
	}

	public IQuader getQ3() {
		return q[2];
	}

	public IQuader getQ4() {
		return q[3];
	}

	public void setQ1(IQuader q1) {
		this.q[0] = q1;
	}

	public IQuader getQ1() {
		return q[0];
	}
	
	public TetrisPoint[] getInitialView(){
		return turn[0];		
	}
}
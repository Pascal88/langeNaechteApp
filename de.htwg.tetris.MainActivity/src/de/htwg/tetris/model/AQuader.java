package de.htwg.tetris.model;

public abstract class AQuader implements IQuader{

	private static final long serialVersionUID = -7838056360000115731L;
	private states state = states.FREE;
	private ITetrisColor color = new TetrisColor(90, 90, 180);

	public AQuader() {
	}
	
	public AQuader(ITetrisColor f) {
		this.color = f;
	}

	public void setColor(ITetrisColor f) {
		this.color = f;
	}

	public ITetrisColor getColor() {
		return color;
	}

	public void setState(states state) {
		this.state = state;
	}

	public states getState() {
		return state;
	}
	
	public abstract int getX();
	public abstract void setX(int x);
	public abstract void setXY(double d, double e);
	public abstract int getY();
	public abstract void setY(int y);
}

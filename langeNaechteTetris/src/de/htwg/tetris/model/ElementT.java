package de.htwg.tetris.model;

public class ElementT extends Element {
	
	private static final long serialVersionUID = -4121722183921662600L;

	public ElementT(){
		this.name ='T';
		this.colorElement = new TetrisColor(128, 0, 255);
		TetrisPoint tmpTurn[][] = 	{
										{new TetrisPoint(0,0),new TetrisPoint(1,0),new TetrisPoint(-1,0),new TetrisPoint(0,-1)},
										{new TetrisPoint(0,0),new TetrisPoint(1,0),new TetrisPoint(0,1),new TetrisPoint(0,-1)},
										{new TetrisPoint(0,0),new TetrisPoint(1,0),new TetrisPoint(0,1),new TetrisPoint(-1,0)},
										{new TetrisPoint(0,0),new TetrisPoint(0,-1),new TetrisPoint(0,1),new TetrisPoint(-1,0)}
									};
		this.turn = tmpTurn;
		this.setTurnStatus(1);
	}

}

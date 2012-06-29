package de.htwg.tetris.model;

public class ElementN extends Element
{
	private static final long serialVersionUID = -2540109948122762050L;

	public ElementN(){
		this.name ='N';
		this.colorElement = new TetrisColor(0, 255, 0);
		TetrisPoint tmpTurn[][] = 	{
										{new TetrisPoint(0,0),new TetrisPoint(0,-1),new TetrisPoint(1,0),new TetrisPoint(1,1)},
										{new TetrisPoint(0,0),new TetrisPoint(-1,0),new TetrisPoint(0,-1),new TetrisPoint(1,-1)},
										{new TetrisPoint(0,0),new TetrisPoint(0,-1),new TetrisPoint(1,0),new TetrisPoint(1,1)},
										{new TetrisPoint(0,0),new TetrisPoint(-1,0),new TetrisPoint(0,-1),new TetrisPoint(1,-1)}
									};
		this.turn = tmpTurn;
		this.setTurnStatus(1);
	}
}
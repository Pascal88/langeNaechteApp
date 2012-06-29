package de.htwg.tetris.model;

public class ElementZ extends Element
{	
	private static final long serialVersionUID = 1158769366082122013L;

	public ElementZ(){
		this.name ='Z';
		this.colorElement = new TetrisColor(255, 0, 0);
		TetrisPoint tmpTurn[][] = 	{
			{new TetrisPoint(0,0),new TetrisPoint(1,-1),new TetrisPoint(1,0),new TetrisPoint(0,1)},
			{new TetrisPoint(0,0),new TetrisPoint(-1,-1),new TetrisPoint(0,-1),new TetrisPoint(1,0)},
			{new TetrisPoint(0,0),new TetrisPoint(1,-1),new TetrisPoint(1,0),new TetrisPoint(0,1)},
			{new TetrisPoint(0,0),new TetrisPoint(-1,-1),new TetrisPoint(0,-1),new TetrisPoint(1,0)}
		};
		this.turn = tmpTurn;	
		this.setTurnStatus(1);
	}
}
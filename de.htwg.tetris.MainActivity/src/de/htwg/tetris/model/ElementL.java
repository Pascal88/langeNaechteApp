package de.htwg.tetris.model;

public class ElementL extends Element
{	
	private static final long serialVersionUID = -8376364262692937869L;

	public ElementL(){
		this.name ='L';
		this.colorElement = new TetrisColor(255, 153, 0);
		TetrisPoint tmpTurn[][] = 	{
										{new TetrisPoint(0,0),new TetrisPoint(0,1),new TetrisPoint(0,-1),new TetrisPoint(1,1)},
										{new TetrisPoint(0,0),new TetrisPoint(1,0),new TetrisPoint(-1,0),new TetrisPoint(-1,1)},
										{new TetrisPoint(0,0),new TetrisPoint(0,-1),new TetrisPoint(0,1),new TetrisPoint(-1,-1)},
										{new TetrisPoint(0,0),new TetrisPoint(-1,0),new TetrisPoint(1,0),new TetrisPoint(1,-1),}
									};
		this.turn = tmpTurn;
		this.setTurnStatus(1);
	}
}

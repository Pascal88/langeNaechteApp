package de.htwg.tetris.model;

public class ElementJ extends Element {

	private static final long serialVersionUID = 2707173402587432008L;

	public ElementJ(){
		this.name ='J';
		this.colorElement = new TetrisColor(0, 51, 255);
		TetrisPoint tmpTurn[][] = 	{
										{new TetrisPoint(0,0),new TetrisPoint(0,1),new TetrisPoint(0,-1),new TetrisPoint(-1,1)},
										{new TetrisPoint(0,0),new TetrisPoint(1,0),new TetrisPoint(-1,0),new TetrisPoint(-1,-1)},
										{new TetrisPoint(0,0),new TetrisPoint(0,-1),new TetrisPoint(0,1),new TetrisPoint(1,-1)},
										{new TetrisPoint(0,0),new TetrisPoint(-1,0),new TetrisPoint(1,0),new TetrisPoint(1,1),}
									};
		this.turn = tmpTurn;
		this.setTurnStatus(1);
	}

}

package de.htwg.tetris.model;

public class ElementI extends Element
{
	private static final long serialVersionUID = -8934069752890984881L;

	public ElementI(){
		this.name = 'I';
		this.colorElement = new TetrisColor(0, 255, 255);
		TetrisPoint[][] tmpTurn = {
										{new TetrisPoint(0,0),new TetrisPoint(0,-1),new TetrisPoint(0,1),new TetrisPoint(0,2)},
										{new TetrisPoint(0,0),new TetrisPoint(-1,0),new TetrisPoint(1,0),new TetrisPoint(2,0)},
										{new TetrisPoint(0,1),new TetrisPoint(0,1),new TetrisPoint(0,-1),new TetrisPoint(0,-2)},
										{new TetrisPoint(0,0),new TetrisPoint(1,0),new TetrisPoint(-1,0),new TetrisPoint(-2,0),}
									};
		this.turn = tmpTurn;
		this.setTurnStatus(1);
	}
}
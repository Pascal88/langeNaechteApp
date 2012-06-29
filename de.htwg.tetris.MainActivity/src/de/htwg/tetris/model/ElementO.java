package de.htwg.tetris.model;

public class ElementO extends Element
{
	private static final long serialVersionUID = 572655513230307894L;

	public ElementO(){
		this.name ='O';
		this.colorElement = new TetrisColor(255, 255, 0);
		TetrisPoint tmpTurn[][] = 	{
										{new TetrisPoint(0,0),new TetrisPoint(0,1),new TetrisPoint(1,0),new TetrisPoint(1,1)}
									};
		this.turn = tmpTurn;	
		this.setTurnStatus(1);
	}
}
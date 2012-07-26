package de.htwg.tetris.controller;

public class GameMechanic implements Runnable
{
	private IGameController gameController = null;
	private volatile Boolean isTetrisRun = true;
	private volatile int count = 0;
	private volatile int tMove = 320;

	public GameMechanic() 
	{
		this.gameController = GameController.INSTANCE;	
	}
	
	public void shutdown(){
		this.isTetrisRun = false;
	}
	
	public boolean isAlive(){
		return this.isTetrisRun;
	}
	
	public void run() 
	{
		while (isTetrisRun)
		{
			while (isTetrisRun && gameController.getElement().getState())
			{
				try {
					Thread.sleep(tMove);
				} 
				catch (InterruptedException e) 
				{
					shutdown();
				}
				synchronized(this.gameController){
					gameController.moveDown();
				}
			}
			
			count++;
			if(count == 15)
			{
				count = 0;
				tMove = tMove-(tMove/5);
			}
		}	
	}
}

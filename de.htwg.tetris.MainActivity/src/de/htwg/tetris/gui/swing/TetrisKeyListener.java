package de.htwg.tetris.gui.swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.htwg.tetris.controller.GameController;
import de.htwg.tetris.controller.IGameController;

public class TetrisKeyListener implements KeyListener
{
	private IGameController gameController;

	public TetrisKeyListener()
	{
		this.gameController = GameController.INSTANCE;
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.CHAR_UNDEFINED) // Button pressed
		{
			int key = e.getKeyCode();
			switch(key) {
				case KeyEvent.VK_LEFT:
					gameController.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					gameController.moveRight();
					break;
				case KeyEvent.VK_UP:
					gameController.moveUp();
					break;
				default:
					gameController.moveDown();
			}
		}		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// not used
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// notused
		
	}
	
}

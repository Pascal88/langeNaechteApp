package de.htwg.tetris.gui.swing;

import java.awt.Graphics;

public interface IGameField  {

	public static int WIDTH = 10; // number of elements in the width of the gamefield 
	public final static int HEIGHT = 20; // number of elements in the height of the gamefield 

	public void resetGameField();

	/**
	 *  paint the Array
	 */
	public void paintComponent(Graphics g);

}
package de.htwg.tetris.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import de.htwg.tetris.controller.ITetrisController;
import de.htwg.tetris.controller.TetrisController;

public class SaveGameListener implements ActionListener {
	
	private ITetrisController tetrisController = null;
	
	public SaveGameListener() {
		this.tetrisController = TetrisController.INSTANCE;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		tetrisController.save((String)JOptionPane.showInputDialog(null, "Type a File Name for your save Game!"));		
	}
}

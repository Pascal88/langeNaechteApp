package de.htwg.tetris.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import de.htwg.tetris.controller.ITetrisController;
import de.htwg.tetris.controller.TetrisController;

public class LoadGameListener implements ActionListener {
	
	private ITetrisController tetrisController = null;
	
	public LoadGameListener () {
		this.tetrisController = TetrisController.INSTANCE;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] possibilities = tetrisController.load();
		tetrisController.load((String)JOptionPane.showInputDialog(null, "Select Save Game!","Load", JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]));
	}

}

package de.htwg.tetris.gui.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import de.htwg.tetris.gui.IGui;

public class Gui extends JFrame implements IGui
{

	private static final long serialVersionUID = 1L;

	private final int layoutGameGridY = 2;
	private final int layoutGameGridX = 2;
	private final int layoutGameIpadX = 290;
	private final int layoutGameIpadY = 590;
	private final int layoutMenuGridY = 0;
	private final int layoutMenuGridWidth = 3;
	private final int sizeWidth = 500;
	private final int sizeHeight = 680;
	
	private JMenuBar menuBar = null;
	private JMenu gameMenu = null;
	private JMenu editMenu = null;
	private JMenuItem newMenuItem = null;
	private JMenuItem exitMenuItem = null;
	private JMenuItem saveMenuItem = null;
	private JMenuItem loadMenuItem = null;
	
	private JTextField textField = null;

	private GridBagLayout layout = null;
	private GridBagConstraints layoutMenu = null;
	private GridBagConstraints layoutGame = null;
	private GridBagConstraints layoutText = null;
	
	private TetrisKeyListener keyListener = null;
	private NewGameListener newGameListener = null;
	private SaveGameListener saveGameListener = null;
	private LoadGameListener loadGameListener = null;
	private IGameField gameField= null;

	public Gui() 
	{
		super("Tetris");
		this.newGameListener = new NewGameListener();
		this.keyListener = new TetrisKeyListener();
		this.saveGameListener = new SaveGameListener();
		this.loadGameListener = new  LoadGameListener();
		this.gameField = new GameField();

		
		try {
			menuBar = new JMenuBar();
			gameMenu = new JMenu();
			editMenu = new JMenu();
			newMenuItem = new JMenuItem();
			exitMenuItem = new JMenuItem();
			saveMenuItem = new JMenuItem();
			loadMenuItem = new JMenuItem();
			textField = new JTextField();

			layout = new GridBagLayout();
			layoutMenu = new GridBagConstraints();
			layoutGame = new GridBagConstraints();
			layoutText = new GridBagConstraints();
			
			addKeyListener(this.keyListener);			
			guiInit();
		
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		validate();

		setWindowCenter();
		setVisible(true);
	}


	private final void guiInit() throws Exception {
		Container panel = getContentPane();
		panel.setBackground(Color.gray);

		Dimension size = this.getSize();
		size.width = sizeWidth;
		size.height = sizeHeight;
		setMinimumSize(size);

		panel.setLayout(layout);

		// Menu
		initMenu();
		layoutMenu.gridy = layoutMenuGridY;
		layoutMenu.gridwidth = layoutMenuGridWidth;
		layoutMenu.fill = GridBagConstraints.NONE;


		// Game
		layoutGame.gridy = layoutGameGridY;
		layoutGame.gridx = layoutGameGridX;
		layoutGame.ipadx = layoutGameIpadX;
		layoutGame.ipady = layoutGameIpadY;
		layoutGame.fill = GridBagConstraints.CENTER;
		
		layoutText.gridx = 1;
		layoutText.gridy = 1;
		layoutText.insets = new Insets(0,0,10,10);
		layoutText.fill = GridBagConstraints.NONE;
		
		textField.setEnabled(false);
		textField.setText("00000");

		// Border
		layout.setConstraints(gameMenu, layoutMenu);
		layout.setConstraints((Component)gameField, layoutGame);
		layout.setConstraints(textField, layoutText);

		this.setJMenuBar(menuBar);
		panel.add(textField);
		panel.add((Component)gameField);
	}

	void initMenu() {
		// Menu
		gameMenu.setText("Spiel");

		newMenuItem.setText("Neues Spiel");
		newMenuItem.addActionListener(newGameListener);
		
		exitMenuItem.setText("Beenden");
		exitMenuItem.addActionListener(new ExitGameListener());
		
		editMenu.setText("Bearbeiten");
		
		saveMenuItem.setText("Speichern");
		saveMenuItem.addActionListener(saveGameListener);
		
		loadMenuItem.setText("Laden");
		loadMenuItem.addActionListener(loadGameListener);
		
		// put Menus together
		gameMenu.add(newMenuItem);
		gameMenu.add(exitMenuItem);
		menuBar.add(gameMenu);
		
		editMenu.add(saveMenuItem);
		editMenu.add(loadMenuItem);
		menuBar.add(editMenu);
	}

	// Override, so you can exit the game 
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}

	public void setTextFieldValue(int highscore) {
		textField.setText(new Integer(highscore).toString());
	}
	
	public void resetTextField() {
		textField.setText(new Integer(0).toString());
	}
	
	private final void setWindowCenter() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		this.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
	}	
}

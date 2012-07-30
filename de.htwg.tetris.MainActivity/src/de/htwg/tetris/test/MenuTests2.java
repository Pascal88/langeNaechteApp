package de.htwg.tetris.test;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import de.htwg.tetris.controller.HighscoreController;
import de.htwg.tetris.gui.activities.GameActivity;
import de.htwg.tetris.gui.activities.HighscoresActivity;
import de.htwg.tetris.gui.activities.MenuActivity;

public class MenuTests2 extends ActivityInstrumentationTestCase2<MenuActivity>{
	
	private Solo solo;
	
	public MenuTests2(){
		super(MenuActivity.class);
	}
	
	public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        new HighscoreController();
	}

	public void testIsRightActivity(){
		solo.assertCurrentActivity("MenuActivity", MenuActivity.class);
	}
	
	public void testStartButton(){
		solo.clickOnText("Start");
		solo.assertCurrentActivity("GameActivity", GameActivity.class);
		solo.goBack();
		solo.clickOnText("Back to Menu");
		solo.assertCurrentActivity("MenuActivity", MenuActivity.class);
	}
	
	public void testHighScoresButton(){
		solo.clickOnText("Highscores");
		solo.assertCurrentActivity("HighscoresActivity", HighscoresActivity.class);
		solo.goBack();
	}
	
	public void testFeedbackButton(){
		solo.clickOnText("Feedback");
		solo.clickOnText("No Thanks");
	}
	
	public void testExitButton(){
		solo.clickOnText("Exit");
	}
}

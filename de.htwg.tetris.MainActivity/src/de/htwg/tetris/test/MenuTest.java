package de.htwg.tetris.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import de.htwg.tetris.R;
import de.htwg.tetris.gui.activities.MenuActivity;

public class MenuTest extends ActivityInstrumentationTestCase2<MenuActivity> {

	public MenuTest() {
		super(MenuActivity.class);
	}
	
	public void testStartButtonsExists(){
		MenuActivity activity = getActivity();
		Button view = (Button) activity.findViewById(R.id.NewGameButton);
		assertNotNull(view);
	}
	
	public void testHighscoreButtonsExists(){
		MenuActivity activity = getActivity();
		Button view = (Button) activity.findViewById(R.id.HighscoresButton);
		assertNotNull(view);
	}
	
	public void testFeedbackButtonsExists(){
		MenuActivity activity = getActivity();
		Button view = (Button) activity.findViewById(R.id.FeedbackButton);
		assertNotNull(view);
	}
	
	public void testExitButtonsExists(){
		MenuActivity activity = getActivity();
		Button view = (Button) activity.findViewById(R.id.ExitButton);
		assertNotNull(view);
	}
}

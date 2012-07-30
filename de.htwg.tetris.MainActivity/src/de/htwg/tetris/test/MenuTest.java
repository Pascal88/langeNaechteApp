package de.htwg.tetris.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import de.htwg.tetris.R;
import de.htwg.tetris.gui.activities.MenuActivity;

public class MenuTest extends ActivityUnitTestCase<MenuActivity> {

	public MenuTest() {
		super(MenuActivity.class);
	}
	
	protected void setUp() throws Exception{
		startActivity(new Intent(getInstrumentation().getTargetContext(),MenuActivity.class),null,null);
	}
	
	protected void tearDown() {
		
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

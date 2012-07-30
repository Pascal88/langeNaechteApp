package de.htwg.tetris.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import de.htwg.tetris.R;
import de.htwg.tetris.gui.activities.GameActivity;

public class GameButtonsTest extends ActivityInstrumentationTestCase2<GameActivity>{

	public GameButtonsTest() {
		super(GameActivity.class);
	}
	
	public void testLeftButtonExists(){
		GameActivity activity = getActivity();
		Button view = (Button) activity.findViewById(R.id.LeftButton);
		assertNotNull(view);
	}
	public void testRightButtonExists(){
		GameActivity activity = getActivity();
		Button view = (Button) activity.findViewById(R.id.RightButton);
		assertNotNull(view);
	}
	
	public void testDownButtonExists(){
		GameActivity activity = getActivity();
		Button view = (Button) activity.findViewById(R.id.DownButton);
		assertNotNull(view);
	}
	
	public void testLUpButtonExists(){
		GameActivity activity = getActivity();
		Button view = (Button) activity.findViewById(R.id.UpButton);
		assertNotNull(view);
	}

}

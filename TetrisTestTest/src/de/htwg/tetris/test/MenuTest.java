package de.htwg.tetris.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import de.htwg.tetris.gui.activities.MenuActivity;

public class MenuTest extends ActivityUnitTestCase<MenuActivity> {

	public MenuTest() {
		super(MenuActivity.class);
	}
	
	protected void setUp() {
		startActivity(new Intent(getInstrumentation().getTargetContext(),MenuActivity.class),null,null);
	}
	
	protected void tearDown() {
		
	}
	
}

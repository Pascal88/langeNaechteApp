package de.htwg.tetris.gui.activities;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.Random;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.util.Log;
import de.htwg.tetris.R;

/**
 * The Class MyApp is the singleton of this App.
 *
 * @author Sebastian Guillen
 */

public class MyApp extends Application {

	private static final String TAG = MyApp.class.getSimpleName();
		
	 /**Show only best 5 scores*/
	 public static final int NUM_OF_HIGHSCORES = 5;
	 /* Shared Preferences */
	 public static final String HIGHSCORES = "tetris_highscores";

	//For randomizing Ok buttons	
	private final static Random rand = new Random();
	
	/**
	 * Randomises text for OK buttons.
	 * @return the random text
	 */
	public static String getPositiveText(Resources res) {
	    	String[] all = res.getStringArray(R.array.Positive_Text);
		return all[rand.nextInt(all.length)];
	}
}

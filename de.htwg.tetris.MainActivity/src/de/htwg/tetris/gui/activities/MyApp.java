package de.htwg.tetris.gui.activities;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;
import de.htwg.tetris.R;
import de.htwg.tetris.controller.HighscoreController;
import de.htwg.tetris.model.HighScoreBean;

@SuppressLint("WorldWriteableFiles")
public class MyApp extends Application {

	private static final String TAG = MyApp.class.getSimpleName();

	public static int NUM_OF_HIGHSCORES = 5;
	/* Shared Preferences */
	public static final String HIGHSCORES = "tetris_highscores";

	// For randomizing Ok buttons
	private final static Random rand = new Random();

	/**
	 * Get 5 highest scores from server -asynchronously-
	 * 
	 * @param c the app context
	 */
	public static void getHighscoresFromServer(final Context c) {
		new Thread(new Runnable() {
			public void run() {
				HighScoreBean[] scores = HighscoreController.INSTANCE.loadHighScore(MyApp.NUM_OF_HIGHSCORES);
				if (scores == null)
					Log.e(TAG, "got null (highscores) from server");
				else {
					SharedPreferences sp = c.getSharedPreferences(
							MyApp.HIGHSCORES, MODE_WORLD_WRITEABLE);
					SharedPreferences.Editor editor = sp.edit();
					for (int i = 0; i < scores.length; i++){
						editor.putInt(Integer.toString(i), scores[i].getScore());
						editor.putString(i + " " + scores[i].getScore(), scores[i].getUserName());
					}
					if (!editor.commit())
						Log.e(TAG, "Couldn't set new highscores");
				}
			}
		}, "getHighscoresFromServerThread").start();
	}

	public static void saveHighscoreToServer(final String userName,
			final int newScore) {
		new Thread(new Runnable() {
			public void run() {
				Log.d(TAG, "Posting score " + newScore+" played by " + userName + " to server");
				if (!HighscoreController.INSTANCE.saveHighScore(userName,
						newScore))
					Log.d(TAG, "Problem posting score " + newScore
							+ " to server");
			}
		}, "saveHighscoreToServerThread").start();
	}

	/**
	 * Randomizes text for OK buttons.
	 * 
	 * @return the random text
	 */
	public static String getPositiveText(Resources res) {
		String[] all = res.getStringArray(R.array.Positive_Text);
		return all[rand.nextInt(all.length)];
	}
	
	public static void setNumOfHighscores(int x) {
		MyApp.NUM_OF_HIGHSCORES = x;
	}
}

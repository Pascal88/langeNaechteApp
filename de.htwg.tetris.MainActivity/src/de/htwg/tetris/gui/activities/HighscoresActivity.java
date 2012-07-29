package de.htwg.tetris.gui.activities;

import de.htwg.tetris.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.TableLayout;
import android.widget.TextView;

@SuppressLint("WorldReadableFiles")
public class HighscoresActivity extends Activity {

	private static final String TAG = HighscoresActivity.class.getSimpleName();

	/** Value to return if preference does not exist. */
	public static final int defValue = 0;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscores);
		initScores();
		setListener();
		MyApp.getHighscoresFromServer(getApplicationContext());
		updateGui();
	}

	/**
	 * Create score views
	 */
	private void initScores() {
		Log.d(TAG, "initialize Highscores");
		TableLayout scoreTable = (TableLayout) findViewById(R.id.HighscoresTable);
		Display display = getWindowManager().getDefaultDisplay();
		int screenHeight = display.getHeight();
		int height = (int) (screenHeight * 0.37);
		
		int numOfHighscores = height % 30;
		numOfHighscores = (height-numOfHighscores) / 30;
		MyApp.setNumOfHighscores(numOfHighscores);
		for (int i = 0; i < MyApp.NUM_OF_HIGHSCORES; i++) {
			TextView child = new TextView(getApplicationContext());
			child.setTextSize(30);
			child.setTextColor(getResources().getColor(R.color.solid_black));
			scoreTable.addView(child, i);
		}
	}

	private void setListener() {
		OnSharedPreferenceChangeListener listener = new OnSharedPreferenceChangeListener() {
			public void onSharedPreferenceChanged(
					SharedPreferences sharedPreferences, String key) {
				updateGuiHighscore(key);
			}
		};
		SharedPreferences settings = getSharedPreferences(MyApp.HIGHSCORES,
				MODE_WORLD_READABLE);
		settings.registerOnSharedPreferenceChangeListener(listener);
	}

	/** update all highscores */
	private void updateGui() {
		for (int i = 1; i <= MyApp.NUM_OF_HIGHSCORES; i++)
			updateGuiHighscore(Integer.toString(i));
	}

	private void updateGuiHighscore(String key) {
		int intKey = 0;
		try{
		intKey = Integer.parseInt(key) - 1;// For index 0
		} catch(NumberFormatException e) {
			key = key.substring(0, 1);
			intKey = Integer.parseInt(key) - 1;
		}

		SharedPreferences settings = getSharedPreferences(MyApp.HIGHSCORES,
				MODE_WORLD_READABLE);
		int score = settings.getInt(key, defValue);
		String userName = settings.getString(key + " " + score, "God");
		String format = getString(R.string.Score_format);
		if(userName.length() > 14) {
			userName = userName.substring(0, 14);
		}
		String text = String.format(format, key + ".", score + " " + userName);
		TableLayout scoreTable = (TableLayout) findViewById(R.id.HighscoresTable);
		TextView theScore = (TextView) (scoreTable).getChildAt(intKey);
		theScore.setText(text);
	}

}
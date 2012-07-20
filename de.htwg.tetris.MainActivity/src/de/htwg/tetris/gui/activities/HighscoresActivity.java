package de.htwg.tetris.gui.activities;

import de.htwg.tetris.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * @author Sebastian Guillen
 * 
 */

public class HighscoresActivity extends Activity {

    

    private static final String TAG = HighscoresActivity.class.getSimpleName();
    
   
    
    /** Value to return if preference does not exist. */
    public static final int defValue = 0;

    /** Called when the activity is first created. */
    @Override
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
	TableLayout scoreTable = (TableLayout)findViewById(R.id.HighscoresTable);
	for(int i = 0; i < MyApp.NUM_OF_HIGHSCORES; i++){
	TextView child = new TextView(getApplicationContext());
	child.setTextSize(30);
	child.setTextColor(getResources().getColor(R.color.solid_black));
	scoreTable.addView(child, i);
	}
    }

	@SuppressLint("WorldReadableFiles")
	private void setListener() {
		OnSharedPreferenceChangeListener listener = new OnSharedPreferenceChangeListener() {
		    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		    	updateGuiHighscore(key);
		    }
		};
		SharedPreferences settings = getSharedPreferences(MyApp.HIGHSCORES, MODE_WORLD_READABLE);
		settings.registerOnSharedPreferenceChangeListener(listener);
	}

	/**update all 5 highscores*/
	private void updateGui() {
	    	for(int i = 1; i <= MyApp.NUM_OF_HIGHSCORES; i++)
	    	    updateGuiHighscore(Integer.toString(i));
	}
    
    
    @SuppressLint("WorldReadableFiles")
	private void updateGuiHighscore(String key) {
	int intKey = Integer.parseInt(key)-1;//For index 0
  
	SharedPreferences settings = getSharedPreferences(MyApp.HIGHSCORES, MODE_WORLD_READABLE);
	    int value = settings.getInt(key, defValue);
	    String format = getString(R.string.Score_format);
	    String text = String.format(format, key+".", value);
	    TableLayout scoreTable = (TableLayout)findViewById(R.id.HighscoresTable);
	    TextView theScore = (TextView)(scoreTable).getChildAt(intKey);
	    theScore.setText(text);
	    Log.d(TAG, "Updated score "+ key + " with " + text);
    }

}
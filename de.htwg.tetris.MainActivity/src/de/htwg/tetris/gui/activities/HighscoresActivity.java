package de.htwg.tetris.gui.activities;

import de.htwg.tetris.R;
import de.htwg.tetris.controller.HighscoreController;
import de.htwg.tetris.model.HighScoreBean;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


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
		setListener();
		MyApp.getHighscoresFromServer(getApplicationContext());
		updateGui();
    }

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
    
    
    private void updateGuiHighscore(String key) {
	int intKey = Integer.parseInt(key)-1;//For index 0
  
	SharedPreferences settings = getSharedPreferences(MyApp.HIGHSCORES, MODE_WORLD_READABLE);
	    int value = settings.getInt(key, defValue);
	    String format = getString(R.string.Score_format);
	    String text = String.format(format, key+".", value);
	    View theScore = ((TableLayout)findViewById(R.id.HighscoresTable)).getChildAt(intKey);
	    ((TextView)theScore).setText(text);
	    Log.d(TAG, "Updated score "+ key + " with " + text);
    }

}
package de.htwg.tetris.gui.activities;

import de.htwg.tetris.R;
import de.htwg.tetris.controller.HighscoreController;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * The class MenuActivity
 * The menu.
 * @author Sebastian Guillen
 */

public class MenuActivity extends Activity{

    private static final String TAG = MenuActivity.class.getSimpleName();
    /** Listener to changed difficulty
     * 	Use instance field for listener
     *  It will not be gc'd as long as this instance is kept referenced*/
    private static OnSharedPreferenceChangeListener li = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       	setContentView(R.layout.menu);
        Eula.showEulaRequireAcceptance(this);
        InitButtons();
    }

    @Override
    public void onResume(){
        super.onResume();
    }


    private void InitButtons() {
		Button b = (Button) findViewById(R.id.NewGameButton);
		b.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
			newGameButtonAction(v);
		    }
		});
		b = (Button) findViewById(R.id.HighscoresButton);
		b.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MenuActivity.this, HighscoresActivity.class);
			MenuActivity.this.startActivity(intent);
		    }
		});
		b = (Button) findViewById(R.id.FeedbackButton);
		b.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
			launchFeedbackDialog(v);
		    }
		});
		b = (Button) findViewById(R.id.ExitButton);
		b.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
			exitApp();
		    }
		});
    }

    private void newGameButtonAction(View v) {
	    Toast.makeText(v.getContext(), "Creating new Tetris", Toast.LENGTH_LONG).show();
	    startGameActivity();
    }

	private void startGameActivity() {
	    Intent intent = new Intent();
	    intent.setClass(MenuActivity.this, GameActivity.class);
	    MenuActivity.this.startActivity(intent);
	}
	
	private void exitApp(){
	    Log.d(TAG, "Exiting App");
	    this.finish();
	}	

	private void launchFeedbackDialog(View v) {
		Resources res = getResources();
		String s = getStringformArray(res.getStringArray(R.array.Feedback_Message));
    	final Button b = (Button) findViewById(R.id.FeedbackButton);
    	new AlertDialog.Builder(v.getContext())
    	        .setIcon(R.drawable.ic_launcher)
    	        .setPositiveButton(MyApp.getPositiveText(getResources()), new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int whichButton) {
    	            	visitFeedbackWebsite();                        	
    	            }
    	        })
    	        .setNeutralButton("Rate it", new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int whichButton) {
    	        	// rate this app
    	        	Intent intent = new Intent(Intent.ACTION_VIEW);
    	        	intent.setData(Uri.parse("market://details?id=esis.android.sudoku"));//FIXME test it on device
    	        	startActivity(intent);
    	            }
    	        })
    	        .setNegativeButton(getString(R.string.no_thanks), new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int whichButton) {
    	            	//Just go away.
    	            }
    	        })
    	        .setTitle((String)b.getText())
    	        .setMessage(s)
    	        .show();
	}

	private String getStringformArray(String[] array) {
		String s = "";
		for (String i : array) {
			Log.d(TAG, "concadenate "+i);
			s += i;
		}
		return s;
	}



	/**
	 *  Visit website for feedback
	 TODO update site with real package name (http://tetris.uservoice.com)
	 */
	private void visitFeedbackWebsite() {
        	String url = getString(R.string.feedback_website);
        	Intent i = new Intent(Intent.ACTION_VIEW);
        	i.setData(Uri.parse(url));
        	startActivity(i);
	}
	

}
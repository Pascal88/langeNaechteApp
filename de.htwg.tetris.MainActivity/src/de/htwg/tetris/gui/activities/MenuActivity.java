package de.htwg.tetris.gui.activities;

import de.htwg.tetris.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MenuActivity extends Activity{

    private static final String TAG = MenuActivity.class.getSimpleName();

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       	setContentView(R.layout.menu);
        Eula.showEulaRequireAcceptance(this);
        InitButtons();
    }

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
		Log.d(TAG, "Start a Game");
	    Intent intent = new Intent();
	    intent.setClass(MenuActivity.this, GameActivity.class);
	    MenuActivity.this.startActivity(intent);
	}
	
	private void exitApp(){
	    Log.d(TAG, "Exiting App");
	    this.finish();
	}	

	/**
	 * show dialog where user can decide to give feedback on google marketplace or give suggestions via webpage
	 * @param View v
	 */
	private void launchFeedbackDialog(View v) {
		Log.d(TAG, "Feedback");
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
	 */
	private void visitFeedbackWebsite() {
        	String url = getString(R.string.feedback_website);
        	Intent i = new Intent(Intent.ACTION_VIEW);
        	i.setData(Uri.parse(url));
        	startActivity(i);
	}
	

}
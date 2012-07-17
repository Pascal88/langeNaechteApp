package de.htwg.tetris.gui.activities;

import java.io.DataInputStream;

import de.htwg.tetris.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * This Activity shows a splash screen for a while and then goes to the menu.
 * you can stop the waiting if you tap your device.
 * It is intended to serve as a helper to load stuff, connect, etc.
 * @author Sebastian Guillen
 */

public class SplashActivity extends Activity{

    private static final String TAG = SplashActivity.class.getSimpleName();
    private final int splashTimeinSeconds = 1;
    private Thread splashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        appendVersion();
    	setLayoutListener((RelativeLayout) findViewById(R.id.SplashLayout));
    	//TODO checkForSavedGame(getApplicationContext());
        launchwaitingThread();
    }

    private void appendVersion() {
	String versionCode = "";
	try {
	    versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
	} catch (NameNotFoundException e) {
	    Log.e(TAG, e.getMessage());
	}        
	if (versionCode != "")
	    versionCode = "version " + versionCode + "\n";
	
	TextView tv = (TextView) findViewById(R.id.SplashText);
        tv.setText(versionCode + tv.getText());
    }
    
    /**
     * Thread for displaying the SplashScreen
     */
    private void launchwaitingThread() {
	this.splashThread =  new Thread() {
	     public void run() {
		 try {
		     //TODO get highscores maybe (server)?
		     sleep(splashTimeinSeconds * 1000);
		     startNextActivity();
		 } catch(InterruptedException e) {
		     Log.d(TAG, "splashThread Interupted:  "+ e.getMessage());
		 } 
	     }
	};
	this.splashThread.start();
    }

    private void setLayoutListener(RelativeLayout splashLayout) {
	splashLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(@SuppressWarnings("unused") View v) {
            	startNextActivity();
            }
    	});
    }

	@Override
	public void onBackPressed() {
	    	splashThread.interrupt();
		super.onBackPressed();	    	
	}
    	private void startNextActivity() { 
    	    	// Interrupt so startNextActivity() isn't called again    	    	splashThread.interrupt();		finish();
		Intent intent = new Intent();
		intent.setClass(SplashActivity.this, MenuActivity.class);
		SplashActivity.this.startActivity(intent);
    	}
}

package de.htwg.tetris.gui.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import de.htwg.tetris.R;
import de.htwg.tetris.Tetris;
import de.htwg.tetris.controller.GameController;
import de.htwg.tetris.controller.IGameController;
import de.htwg.tetris.controller.IMechanikController;
import de.htwg.tetris.controller.ITetrisController;
import de.htwg.tetris.controller.MechanikController;
import de.htwg.tetris.controller.TetrisController;
import de.htwg.tetris.model.IGameArray;
import de.htwg.tetris.observer.IObserverNewElement;

/**
 * The class GameActivity
 * 
 * @author Sebastian Guillen
 * 
 * TODO 's: fill stubs
 * 	   
 */

public class GameActivity extends Activity {

	private static final String TAG = GameActivity.class.getSimpleName();
	private TextView score;
	private IMechanikController mechanikController = null;
	private IGameController gameController = null;
	private IGameArray gameArray = null;
	private ITetrisController tetrisController;
	private IGameArray persistentGameArray = null;
	private Tetris tetris = null;

	/** Called when the activity is first created. */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.tetris = new Tetris();
		setContentView(R.layout.game);
		initScoreBar();
		initButtons();
		MyApp.getHighscoresFromServer(getApplicationContext());
	}
	
	protected void onStart(){
		super.onStart();
		if(this.tetris == null){
			this.tetris = new Tetris();
		}
		gameController = GameController.INSTANCE;
		mechanikController =  MechanikController.INSTANCE;
		tetrisController = TetrisController.INSTANCE;
		this.gameArray = this.gameController.getSpielarray();
		findViewById(R.id.GameFieldId);
		Log.d("lala", "start");
	}
	
	protected void onResume(){
		super.onResume();
		newGame();
		if(this.persistentGameArray != null) {
			this.gameArray = this.persistentGameArray;
			this.gameController.setSpielarray(this.persistentGameArray);
		}
	}
	
	protected void onPause(){
		super.onPause();
		this.persistentGameArray = this.gameArray;
	}
	
	protected void onStop() {
		super.onStop();
		if(this.mechanikController.isMechanikAlive()){
			this.mechanikController.stopMechanic();
		}
		this.tetris.finalize();
		this.tetris = null;
		Log.d("lala","stop");
	}
	
	public void onBackPressed() {
		this.mechanikController.stopMechanic();
		askForRestartOrLeave();
	}

	private void initScoreBar() {
		score = (TextView) findViewById(R.id.Score);
		//updateGuiScore(0);
	}

	public void updateGuiScore(int x) {
		score.setText(x);
		//TODO TetrisController.INSTANCE.getHighscore());
	}

	private void initButtons() {
		Button b = (Button) findViewById(R.id.LeftButton);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.d("lala", "left");
				leftButtonAction();
			}
		});
		b = (Button) findViewById(R.id.RightButton);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				rightButtonAction();
			}
		});
		b = (Button) findViewById(R.id.UpButton);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				upButtonAction();
			}
		});
		b = (Button) findViewById(R.id.DownButton);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				downButtonAction();
			}
		});

	}

	private void leftButtonAction() {
		this.gameController.moveLeft();

	}

	private void rightButtonAction() {
		this.gameController.moveRight();

	}

	private void upButtonAction() {
		this.gameController.moveUp();

	}

	private void downButtonAction() {
		this.gameController.moveDown();

	}

	private void newGame() {
		Log.d(TAG, "New Game");
		//...
		ResetGame();
	}

	private void ResetGame() {
		// TODO Restore speed of game
		if(this.mechanikController.isMechanikAlive()){
			this.mechanikController.stopMechanic();
		}
		this.gameController.resetGame();
		this.mechanikController.newMechanik();
		repaint();
		// Score reset
	}

	/** TODO
	 * Called everytime the gui gets updated
	 **/
	private void repaint() {
		//updateGuiScore(0);
		//...
	}

	//TODO call when game ends
	private void gameEnded() {
		Log.d(TAG, "Game Ended");
		showGameEndedMessage();
		saveHighscore(this.tetrisController.getHighscore());
	}
	

	private void showGameEndedMessage() {
		new AlertDialog.Builder(this)
		.setIcon(R.drawable.ic_launcher)
		.setPositiveButton(MyApp.getPositiveText(getResources()),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				ResetGame();
			}
		})
		.setNegativeButton("Menu",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				exitGame();
			}
		})
		.setCancelable(false)
		.setMessage(getString(R.string.gameEnded))
		.show();
	}

	private void saveHighscore(int newScore) {

		String worstScoreIndex = Integer.toString(MyApp.NUM_OF_HIGHSCORES);
		SharedPreferences settings = getSharedPreferences(MyApp.HIGHSCORES, MODE_WORLD_READABLE);
		int worstScore = settings.getInt(worstScoreIndex,
				HighscoresActivity.defValue);
		if (newScore > worstScore)
			Toast.makeText(this, "New Highscore: " + newScore, Toast.LENGTH_SHORT).show();
		MyApp.saveHighscoreToServer("DEBUG_USER",newScore);//FIXME get username: Name must have no spaces (Illegal character in query exception)
	}

	private void askForRestartOrLeave() {
		String msg = getString(R.string.RestartOrLeave);
		AlertDialog d = new AlertDialog.Builder(this)
		.setIcon(R.drawable.ic_action_search)
		.setPositiveButton(getString(R.string.resume),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int whichButton) {
				MechanikController.INSTANCE.newMechanik();
			}
		})
		.setNegativeButton(R.string.backToMenu,
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int whichButton) {
				exitGame();
			}
		})
		.setMessage(msg)
		.show();
	}

	private void exitGame() {
		Log.d(TAG, "Exiting Game");
		ResetGame();
		this.persistentGameArray = null;
		this.finish();
	}
}

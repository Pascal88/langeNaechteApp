package de.htwg.tetris.gui.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import de.htwg.tetris.controller.HighscoreController;
import de.htwg.tetris.controller.IGameController;
import de.htwg.tetris.controller.IMechanikController;
import de.htwg.tetris.controller.MechanikController;
import de.htwg.tetris.controller.TetrisController;
import de.htwg.tetris.model.HighScoreBean;
import de.htwg.tetris.model.IGameArray;

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
	private Tetris backendTetris;// The class to restart a game
	private TextView score;
	GameView gameField;
	private IMechanikController mechanikController = null;
	private IGameController gameController = null;
	private IGameArray gameArray = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		backendTetris = new Tetris();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		this.gameController = GameController.INSTANCE;
		this.mechanikController = MechanikController.INSTANCE;
		this.gameArray = this.gameController.getSpielarray();
		
		initScoreBar();
		initButtons();
		initGameField();
		newGame();
		MyApp.getHighscoresFromServer(getApplicationContext());
		//Zum testen saveHighscore(666);
	}

	@Override
	public void onBackPressed() {
		askForRestartOrLeave();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void initGameField() {
		//TODO change the type of layout to something useful
		GameView gameField = (GameView) findViewById(R.id.GameFieldId);

	}

	private void initScoreBar() {
		score = (TextView) findViewById(R.id.Score);
		updateGuiScore();
	}

	private void updateGuiScore() {
		score.setText("Replace this with score");
		//TODO TetrisController.INSTANCE.getHighscore());
	}

	private void initButtons() {
		Button b = (Button) findViewById(R.id.LeftButton);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
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
		// TODO Auto-generated method stub

	}

	private void rightButtonAction() {
		// TODO Auto-generated method stub

	}

	private void upButtonAction() {
		// TODO Auto-generated method stub

	}

	private void downButtonAction() {
		// TODO Auto-generated method stub

	}

	private void newGame() {
		Log.d(TAG, "New Game");
		//...
		ResetGame();
	}

	private void ResetGame() {
		// TODO Restore speed of game
		this.mechanikController.stopMechanic();
		this.gameController.resetGame();
		this.gameController.newElement();
		this.gameArray.elementMergeArray(this.gameController.getElement());

		this.mechanikController.newMechanik();
		repaint();
		// Score reset
	}

	/** TODO
	 * Called everytime the gui gets updated
	 **/
	private void repaint() {
		updateGuiScore();
		//...
	}

	//TODO call when game ends
	private void gameEnded() {
		Log.d(TAG, "Game Ended");
		showGameEndedMessage();
		saveHighscore(TetrisController.INSTANCE.getHighscore());
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
		.setPositiveButton(getString(R.string.restart),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int whichButton) {
				ResetGame();
			}
		})
		.setNegativeButton("Outta here",
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
		this.finish();
	}

}

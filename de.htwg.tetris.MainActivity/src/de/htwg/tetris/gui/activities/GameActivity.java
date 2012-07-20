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
	private List<IObserverNewElement> observersNewElement;
	private ITetrisController tetrisController;
	private GameView gameField;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		observersNewElement = new ArrayList<IObserverNewElement>();
		gameController = new GameController(observersNewElement);
		setContentView(R.layout.game);
		
		initScoreBar();
		initButtons();
		initGameField();
		this.mechanikController =  new MechanikController();
		tetrisController = new TetrisController(GameController.INSTANCE, MechanikController.INSTANCE);
		observersNewElement.add(tetrisController);
		this.gameArray = this.gameController.getSpielarray();
		MyApp.getHighscoresFromServer(getApplicationContext());
		setContentView(R.layout.game);
		newGame();
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
		gameField = (GameView) findViewById(R.id.GameFieldId);
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
		saveHighscore(this.tetrisController.getHighscore());
	}
	
	private void startGame(){
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
		this.mechanikController.stopMechanic();
		this.finish();
	}
}

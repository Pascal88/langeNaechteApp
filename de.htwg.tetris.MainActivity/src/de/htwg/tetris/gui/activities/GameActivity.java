package de.htwg.tetris.gui.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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

@SuppressLint("WorldReadableFiles")
public class GameActivity extends Activity implements IObserverNewElement{

	private static final String TAG = GameActivity.class.getSimpleName();
	private TextView score;
	private IMechanikController mechanikController = null;
	private IGameController gameController = null;
	private IGameArray gameArray = null;
	private ITetrisController tetrisController;
	private IGameArray persistentGameArray = null;
	private Tetris tetris = null;
	private int eventX, eventY;

	/** Called when the activity is first created. */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tetris = new Tetris();
		setContentView(R.layout.game);
		initScoreBar();
		initButtons();
		//MyApp.getHighscoresFromServer(getApplicationContext());
	}
	
	protected void onStart(){
		super.onStart();
		if(tetris == null){
			tetris = new Tetris();
		}
		gameController = GameController.INSTANCE;
		mechanikController =  MechanikController.INSTANCE;
		tetrisController = TetrisController.INSTANCE;
		gameArray = gameController.getGameArray();
		gameController.getObserversNewElement().add(this);
	}
	
	protected void onResume(){
		super.onResume();
		ResetGame();
		if(persistentGameArray != null) {
			gameArray = persistentGameArray;
			gameController.setGameArray(persistentGameArray);
		}
	}
	
	protected void onPause(){
		super.onPause();
		persistentGameArray = gameArray;
	}
	
	protected void onStop() {
		super.onStop();
		if(mechanikController.isMechanikAlive()){//stop game loop == pause game
			mechanikController.stopMechanic();
		}
		tetris.finalize();//delete all controllers
		tetris = null;
	}
	
	public void onBackPressed() {
		mechanikController.stopMechanic();//stop game loop == pause game
		askForResumeOrLeave();
	}
	
	/**
	 * capture Touch begin or end
	 */
	public boolean onTouchEvent(MotionEvent event){
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				eventX = (int) event.getX();
				eventY = (int) event.getY();
				break;
			case MotionEvent.ACTION_UP:
				processTouch(eventX, eventY, (int) event.getX(), (int) event.getY());
				break;
			default:
				return false;
		}
		return true;		
	}
	
	/**
	 * @desc find out which move the user wants to do
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	private void processTouch(int startX, int startY, int endX, int endY){
		
		int diffX = endX-startX;
		int diffY = endY-startY;
		
		if(diffX > 0) {
			if(diffY > 0){
				if(diffX > diffY){
					Log.d(TAG, "Move Right");
					gameController.moveRight();
				}else if (diffY > diffX){
					Log.d(TAG, "Move Down");
					gameController.moveDown();
				}
			} else if (diffY < 0){
				diffY *= -1;
				if(diffX > diffY){
					Log.d(TAG, "Move Right");
					gameController.moveRight();
				}else if (diffY > diffX){
					Log.d(TAG, "Move Up");
					gameController.moveUp();
				}
			}
		} else if(diffX < 0) {
			if(diffY > 0){
				diffX *= -1;
				if(diffX > diffY){
					Log.d(TAG, "Move Left");
					gameController.moveLeft();
				}else if (diffY > diffX){
					Log.d(TAG, "Move Down");
					gameController.moveDown();
				}
			} else if (diffY < 0){
				diffY *= -1;
				diffX *= -1;
				if(diffX > diffY){
					Log.d(TAG, "Move Left");
					gameController.moveLeft();
				}else if (diffY > diffX){
					Log.d(TAG, "Move Up");
					gameController.moveUp();
				}
			}
		}
	}
	
	/**
	 * initialize Score Bar
	 */
	private void initScoreBar() {
		score = (TextView) findViewById(R.id.Score);
	}

	/**
	 * update Score Bar needs to be executed on UI Thread
	 */
	public  void updateGuiScore() {
		runOnUiThread(new Runnable() {
		     public void run() {

		    	 score.setText(""+tetrisController.getHighscore());

		    }
		});
	}

	/**
	 * initialize Buttons
	 */
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
		gameController.moveLeft();

	}

	private void rightButtonAction() {
		gameController.moveRight();

	}

	private void upButtonAction() {
		gameController.moveUp();

	}

	private void downButtonAction() {
		gameController.moveDown();

	}

	/**
	 * Starts/Resets a Tetris Game
	 */
	private void ResetGame() {
		tetrisController.reStartGame();
	}

	/**
	 * shows dialog which asks user if he wants to submit his score
	 */
	private void showGameEndedMessage() {
		final EditText input = new EditText(this);
		final Builder d = new AlertDialog.Builder(this)
		.setIcon(R.drawable.ic_launcher)
		.setPositiveButton(MyApp.getPositiveText(getResources()),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Editable value = input.getText();
				saveHighscore(tetrisController.getHighscore(), value.toString());
				exitGame();
			}
		})
		.setNegativeButton(R.string.backToMenu,
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				exitGame();
			}
		})
		.setCancelable(false)
		.setMessage(getString(R.string.gameEnded))
		.setView(input);
		runOnUiThread(new Runnable() {
		     public void run() {

		    	 d.show();

		    }
		});
	}

	private void saveHighscore(int newScore, String userName) {

		String worstScoreIndex = Integer.toString(MyApp.NUM_OF_HIGHSCORES);
		SharedPreferences settings = getSharedPreferences(MyApp.HIGHSCORES, MODE_WORLD_READABLE);
		int worstScore = settings.getInt(worstScoreIndex,
				HighscoresActivity.defValue);
		if (newScore > worstScore)//notify user if has a new high ranked highscore
			Toast.makeText(this, "New Highscore: " + newScore, Toast.LENGTH_SHORT).show();
		
		MyApp.saveHighscoreToServer(userName,newScore);
	}

	/**
	 * Show dialog which asks the user to Resume the game or go back to Menu
	 */
	private void askForResumeOrLeave() {
		String msg = getString(R.string.RestartOrLeave);
		new AlertDialog.Builder(this)
		.setIcon(R.drawable.ic_action_search)
		.setPositiveButton(getString(R.string.resume),
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int whichButton) {
				MechanikController.INSTANCE.newMechanik();//old Thread(gameLoop) is dead so start new one
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

	/**
	 * back to Menu
	 */
	private void exitGame() {
		ResetGame();
		persistentGameArray = null;
		finish();
	}

	/**
	 * called after Subject notify
	 */
	public void update(int countFullLine) {
		updateGuiScore();
		if (gameController.testGameOver()) {
			showGameEndedMessage();
		}
		
	}
}

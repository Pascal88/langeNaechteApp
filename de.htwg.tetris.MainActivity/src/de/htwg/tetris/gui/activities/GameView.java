package de.htwg.tetris.gui.activities;

import de.htwg.tetris.controller.GameController;
import de.htwg.tetris.controller.IGameController;
import de.htwg.tetris.model.IGameArray;
import de.htwg.tetris.model.IQuader.states;
import de.htwg.tetris.model.ITetrisColor;
import de.htwg.tetris.observer.IObserver;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameView extends View implements IObserver {
	
	private Paint tetrisPaint;
	private IGameArray gameArray = null;
	private IGameController gameController = null;
	private int windowHeight;
	private int windowWidth;
	private int left;
	private int top;
	private int right;
	private int bottom;
	private int qubeSize;
	
	public static int WIDTH = 10;
	public static int HEIGHT = 20;
	
	public GameView(Context context) {
		super(context);
		init();
	}
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		setBackgroundColor(Color.BLACK);
		tetrisPaint = new Paint();
		this.gameController = GameController.INSTANCE;
		
		this.gameArray = this.gameController.getSpielarray();
		this.gameArray.registerObserver(this);
	}
	
	@Override
	 public void onWindowFocusChanged(boolean hasFocus) {
	    super.onWindowFocusChanged(hasFocus);
		calcGameWindowSize(this.getHeight(),this.getWidth());
	 }

	public void onDraw(Canvas canvas){
		
		//frame
		tetrisPaint.setColor(Color.RED);
		tetrisPaint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(left-1, top-1, right+1, bottom+1, tetrisPaint);
		
		//make sure on draw will be called again
		invalidate();
		
		//update game screen
		repaint(canvas);
		
	}
	
	private void calcGameWindowSize(int viewHeight, int viewWidth) {
		
		if(viewHeight == 0 || viewWidth == 0) {
			//error
		} else if(viewHeight > 2*viewWidth) {
			//more height than usable -> width is limit
			windowWidth = viewWidth - (viewWidth % WIDTH);
			windowHeight = windowWidth*2;
			left = (viewWidth-windowWidth)/2;
			right = left+windowWidth;
			top = (viewHeight-windowHeight)/2;
			bottom = top+windowHeight;
			qubeSize = windowWidth/WIDTH;
			
		} else if(viewHeight < viewWidth*2) {
			//more width than usable -> height is limit
			windowHeight = viewHeight - (viewHeight % HEIGHT);
			windowWidth = windowHeight/2;
			left = (viewWidth-windowWidth)/2;
			right = left+windowWidth;
			top = (viewHeight-windowHeight)/2;
			bottom = top+windowHeight;
			qubeSize = viewHeight/HEIGHT;
		}
		
	}
	
	public void update() {}

	private synchronized void repaint(Canvas canvas) {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if (gameArray.getState(i, j) != states.FREE) {
					ITetrisColor c = gameArray.getColor(i,j);
					tetrisPaint = new Paint();
					tetrisPaint.setColor(Color.rgb(c.getR(), c.getG(), c.getB()));
					tetrisPaint.setStyle(Paint.Style.FILL);
					canvas.drawRect((i*qubeSize)+left,(j*qubeSize)+top,((i+1)*qubeSize)+left,((j+1)*qubeSize)+top,tetrisPaint);
				} else {
					tetrisPaint.setColor(Color.GRAY);
					tetrisPaint.setStyle(Paint.Style.FILL);
					canvas.drawRect((i*qubeSize)+left,(j*qubeSize)+top,((i+1)*qubeSize)+left,((j+1)*qubeSize)+top,tetrisPaint);
				}
			}
		}
	}
}

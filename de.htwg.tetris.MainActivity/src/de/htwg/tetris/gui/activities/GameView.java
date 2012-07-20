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
import android.view.View;

public class GameView extends View implements IObserver {
	
	private Paint tetrisPaint;
	private IGameArray gameArray = null;
	private IGameController gameController = null;
	private Canvas myCanvas;
	private int height;
	private int width;
	private int windowHeight;
	private int windowWidth;
	private int left;
	private int top;
	private int right;
	private int bottom;
	private int blockSize;
	private int heightNumber = 20;
	private int widthNumber = 10;

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
		height = this.getHeight();
		width = this.getWidth();
		calcGameWindowSize(height,width);
	 }

	public void onDraw(Canvas canvas){
		
		myCanvas = canvas;
		
		//game area
		tetrisPaint.setColor(Color.GRAY);
		tetrisPaint.setStyle(Paint.Style.FILL);
		canvas.drawRect(left, top, right, bottom, tetrisPaint);
		
		//frame
		tetrisPaint.setColor(Color.RED);
		tetrisPaint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(left, top, right, bottom, tetrisPaint);
	}
	
	private void calcGameWindowSize(int viewHeight, int viewWidth) {
		
		if((viewHeight == 0) || (viewWidth == 0)) {
			//erreur
		}
		if(viewHeight > 2*viewWidth) {
			//more height than usable -> width is limit
			windowWidth = calcNextTenWidth(viewWidth);
			windowHeight = 2*windowWidth;
			left = (viewWidth-windowWidth)/2;
			right = left+windowWidth;
			top = (viewHeight-windowHeight)/2;
			bottom = top+windowHeight;
		} else if(viewHeight < viewWidth*2) {
			//more width than usable -> height is limit
			windowHeight = calcNextTwentyHeight(viewHeight);
			windowWidth = windowHeight/2;
			left = (viewWidth-windowWidth)/2;
			right = left+windowWidth;
			top = (viewHeight-windowHeight)/2;
			bottom = top+windowHeight;
		}
		
	}
	
	//calculates the next smaller number divisible by ten
	private int calcNextTenWidth(int num) {
		//num = num-2;
		num = num/widthNumber;
		blockSize = num;
		num = num*widthNumber;
		return num;
	}
	
	private int calcNextTwentyHeight(int num) {
		//num = num-2;
		num = num/heightNumber;
		blockSize = num;
		num = num*heightNumber;
		return num;
	}

	@Override
	public void update() {
		repaint(myCanvas);
	}

	private void repaint(Canvas canvas) {
		for (int i = 0; i < widthNumber; i++) {
			for (int j = 0; j < heightNumber; j++) {
				if (gameArray.getState(i, j) != states.FREE) {
					ITetrisColor c = gameArray.getColor(i,j);
					tetrisPaint.setColor(Color.rgb(c.getR(), c.getG(), c.getB()));
					canvas.drawRect(i*left,j*top,left+i*blockSize,top+j*blockSize,tetrisPaint);
				}
			}
		}
	}
}

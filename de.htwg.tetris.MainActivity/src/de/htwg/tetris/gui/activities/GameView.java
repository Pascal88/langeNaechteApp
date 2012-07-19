package de.htwg.tetris.gui.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
	
	Paint tetrisPaint;

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
	}
	
	public void onDraw(Canvas canvas){
		
		int left = 10;
		int top = 10;
		int right = 20;
		int bottom = 20;
		
		//game area
		tetrisPaint.setColor(Color.GRAY);
		tetrisPaint.setStyle(Paint.Style.FILL);
		canvas.drawRect(left, top, right, bottom, tetrisPaint);
		
		//rahmen
		tetrisPaint.setColor(Color.RED);
		tetrisPaint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(left, top, right, bottom, tetrisPaint);
	}

}

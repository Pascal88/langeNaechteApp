package de.htwg.tetris.controller;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import de.htwg.tetris.model.HighScoreBean;
import de.htwg.tetris.model.Highscore;
import de.htwg.tetris.model.IHighscore;

public class HighscoreController implements IHighscoreController {

	public static IHighscoreController INSTANCE;
	private IHighscore highScore;
	
	public HighscoreController(){
		if(HighscoreController.INSTANCE == null) {
			HighscoreController.INSTANCE = this;
		}
		
		this.highScore = new Highscore();
	}
	
	public Boolean saveHighScore(String username, int score) {
		try {
			return this.highScore.storeHighScore(username, score);
		} catch (ClientProtocolException e) {
			return false;
		} catch (IOException e) {
			return false;
		} catch (HttpException e) {
			return false;
		}
	}

	public HighScoreBean[] loadHighScore(int count) {
		try {
			return this.highScore.loadHighScore(count);
		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		} catch (HttpException e) {
			return null;
		} catch (JSONException e) {
			return null;
		}
	}

	public IHighscoreController getInstance() {
		return HighscoreController.INSTANCE;
	}

}

package de.htwg.tetris.model;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

public interface IHighscore {
	public Boolean storeHighScore(String userName, int score) throws ClientProtocolException, IOException, HttpException;
	public HighScoreBean[] loadHighScore(int count) throws ClientProtocolException, IOException, HttpException, JSONException;
}

package de.htwg.tetris.model;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpException;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Highscore implements IHighscore {
	
	private HttpClient client;
	
	public Highscore(){
		this.client = new DefaultHttpClient();
	}

	@Override
	public Boolean storeHighScore(String userName, int score) throws ClientProtocolException, IOException, HttpException {
		userName = userName.replaceAll("\\s+", "");
		HttpGet httpGet = new HttpGet("http://htwgtetris.eu5.org/?controller=highscore&action=store&username="+userName+"&score="+score);
		HttpResponse response = this.client.execute(httpGet);
		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		if(statusCode == 118) {
			throw new HttpException("Timeout");
			
		} else if(statusCode == 404) {
			throw new HttpException("Not Found");
			
		} else if(statusCode == 200) {
			return true;
		}
		
		return false;
	}

	@Override
	public HighScoreBean[] loadHighScore(int count) throws ClientProtocolException, IOException, HttpException, JSONException {
		
		String daten = "";
		
		HttpGet httpGet = new HttpGet("http://htwgtetris.eu5.org/?controller=highscore&action=load&count="+count);
		HttpResponse response = this.client.execute(httpGet);
		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		if(statusCode == 118) {
			throw new HttpException("Timeout");
			
		} else if(statusCode == 404) {
			throw new HttpException("Not Found");
			
		} else if(statusCode == 200) {
			HttpEntity entity = response.getEntity();
			BufferedReader reader =  new BufferedReader(new InputStreamReader(entity.getContent()));
			
			String line = "";
			
			while((line = reader.readLine()) != null) {
				daten += line;
			}
		}
		
		daten = daten.substring(daten.indexOf("["), daten.indexOf("]")+1);
		
		JSONArray jsonArray = new JSONArray(daten);
		
		HighScoreBean[] out = new HighScoreBean[jsonArray.length()];
		
		for(int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			out[i] = new HighScoreBean();
			out[i].setUserName(jsonObject.getString("Username"));
			out[i].setScore(jsonObject.getInt("Score"));
		}
		return out;
	}

}

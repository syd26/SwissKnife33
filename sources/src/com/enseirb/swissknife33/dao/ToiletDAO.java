package com.enseirb.swissknife33.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.enseirb.swissknife33.dao.model.ToiletDTO;
import com.enseirb.swissknife33.dao.utils.JsonParser;
import com.enseirb.swissknife33.exception.Swissknife33Exception;
import com.enseirb.swissknife33.parser.ToiletParser;

public class ToiletDAO extends AbstractDAO<ToiletDTO> {
	
	private String PERSISTENCE_KEY_TOILETS = "TOILETS_DATA";
	
	public ToiletDAO(String url, ToiletParser parser, Context context) {
		this.URL = url;
		this.parser = parser;
		this.storage = new Storage(context);
	}
	
	public List<ToiletDTO> fetch() throws JSONException, Swissknife33Exception {
		
		//TODO : Here I tried to make the application offline first and check 
		// if data is cached before fetching it from the web.
		
		List<ToiletDTO> list = fetchFromURL();
		return list;

	}
	
	public List<ToiletDTO> fetchFromCache() throws JSONException, Swissknife33Exception {
		JSONArray jsonDataArray = new JSONArray();
		System.out.println("Fetching toiletsDTO from cache.");
		
		String savedItems = storage.getString(PERSISTENCE_KEY_TOILETS);
		System.out.println(savedItems);
		jsonDataArray = new JSONArray(savedItems);
		
		List<ToiletDTO> list = parser.parse(jsonDataArray);
		return list;
	}

	public List<ToiletDTO> fetchFromURL() throws JSONException, Swissknife33Exception {
		JsonParser JSON = new JsonParser();
		JSONObject jsonResult = new JSONObject();
		System.out.println("Fetching toiletsDTO from URL.");
		
		try {
			jsonResult = JSON.readJsonFromUrl(URL);
		} catch (MalformedURLException e) {
			throw new Swissknife33Exception();
		} catch (IOException e) {
			throw new Swissknife33Exception();
		} catch (JSONException e) {
			throw e;
		}
		
		JSONArray toiletsArray = jsonResult.getJSONArray("d");
		
		//update Cache
		storage.setString(PERSISTENCE_KEY_TOILETS, toiletsArray.toString());
		
		List<ToiletDTO> list = parser.parse(toiletsArray);
		
		return list;
	}
}
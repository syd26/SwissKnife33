package com.enseirb.swissknife33.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.enseirb.swissknife33.dao.model.ParkingDTO;
import com.enseirb.swissknife33.parser.ParkingParser;

import JSONUtils.*;

public class ParkingDAO extends AbstractDAO<ParkingDTO> {
	
	public ParkingDAO(String url) {
		super(url);
		this.URL = url+"sigparkpub/?format=json";
	}


	//private String URL_PARKING = "http://odata.bordeaux.fr/v1/databordeaux/sigparkpub/?format=json";
	

	public List<ParkingDTO> fetch() throws JSONException{
		JsonParser JSON = new JsonParser();
		JSONObject jsonResult = new JSONObject();
		
		try {
			jsonResult = JSON.readJsonFromUrl(this.URL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray parkingsArray = jsonResult.getJSONArray("d");
		ParkingParser parser = new ParkingParser();
		List<ParkingDTO> list = parser.parse(parkingsArray);
		
		return list;
	}
}

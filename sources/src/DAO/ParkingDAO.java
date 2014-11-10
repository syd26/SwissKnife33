package DAO;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import JSONUtils.*;
import DTO.*;

public class ParkingDAO {
	
	private String url;

	public ParkingDAO(String url){
		this.url = url;
	}
	
	public ParkingDTO fetch() throws JSONException{
		JsonParser JSON = new JsonParser();
		JSONObject jsonResult = new JSONObject();
		
		try {
			jsonResult = JSON.readJsonFromUrl(url);
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
		
		return new ParkingDTO(jsonResult);
	}
}

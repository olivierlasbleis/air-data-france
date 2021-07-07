package com.lasbleiso;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lasbleiso.entities.Departement;
import com.lasbleiso.entities.geojson.Coordinate;
import com.lasbleiso.entities.geojson.Feature;
import com.lasbleiso.entities.geojson.Geojson;
import com.lasbleiso.entities.geojson.Geometry;
import com.lasbleiso.entities.geojson.Properties;
import com.lasbleiso.utils.ApiUtils;

public class Dev {
	
	
	static String url = "https://raw.githubusercontent.com/gregoiredavid/france-geojson/master/departements-version-simplifiee.geojson";
	
	
	public static void main(String[] args) throws Exception {
		JSONObject myResponse = new JSONObject(ApiUtils.callApi(url));
		
		 //create ObjectMapper instance
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	    //convert json string to object

	    //ICI
	    Properties feasfdvfeture = objectMapper.readValue(myResponse.getJSONArray("features").getJSONObject(0).getJSONObject("properties").toString(),Properties.class);
	    //Coordinate[] fgf = objectMapper.readValue(myResponse.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates").toString(),Coordinate[].class);
	    //Geometry featdvdure = objectMapper.readValue(myResponse.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").toString(),Geometry.class);
	    Feature[] feafvture = objectMapper.readValue(myResponse.getJSONArray("features").toString(),Feature[].class);
	    Geojson geojson = objectMapper.readValue(myResponse.toString(), Geojson.class); 

	}

}

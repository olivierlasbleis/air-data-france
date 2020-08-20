package com.lasbleiso.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("map")
public class MapController {
	
	@GetMapping("coucou")
	public String getTopojson() {
		return "coucou";
	}
	
	@GetMapping("dir")
	public String getDir() {
		System.out.println(System.getProperty("user.dir")+"\\src\\main\\resources\\data-topojson.properties");
		return System.getProperty("user.dir");
	}
	
	@GetMapping("topojson")
	public String obtenirLaListsfrgrrdgvdeDesCommunes() throws Exception {
		JSONObject jsonTopojson = null;
		try {
		      File myObj = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\data-topojson.txt");
		      @SuppressWarnings("resource")
			Scanner myReader = new Scanner(myObj);
		      String stringTopojson = "";
		      while (myReader.hasNextLine()) {
		    	  String currentData = myReader.nextLine();
		    	  stringTopojson = stringTopojson + currentData;
		      }
		       jsonTopojson = new JSONObject(stringTopojson.toString());
		      
		} catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		return jsonTopojson.toString();
	}
	

}

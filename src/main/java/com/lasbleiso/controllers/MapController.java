package com.lasbleiso.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Scanner;

import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
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
		
		
		return System.getProperty("user.dir").toString();
		
	}
	
	@GetMapping("topojsontest")
	public String obtenirLaListsfrdsrsvgrrdgvdeDesCommunes() throws Exception {
		
		return getResource("data-topojson.txt");
	}
	
	
	
	
	@GetMapping("topojson")
	public String obtenirLaListsfrgrrdgvdeDesCommunes() throws Exception {
		JSONObject jsonTopojson = null;
		try {
			File myObj = new File(
					getClass().getClassLoader().getResource("data-topojson.txt").getFile()
				);
		      //File myObj = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\data-topojson.txt");
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
	
	static String getResource(String rsc) {
	      String val = "";

	      try {
	         Class cls = Class.forName("MapController");

	         // returns the ClassLoader object associated with this Class
	         ClassLoader cLoader = cls.getClassLoader();
	         
	         // input stream
	         InputStream i = cLoader.getResourceAsStream(rsc);
	         BufferedReader r = new BufferedReader(new InputStreamReader(i));

	         // reads each line
	         String l;
	         while((l = r.readLine()) != null) {
	            val = val + l;
	         } 
	         i.close();
	      } catch(Exception e) {
	         System.out.println(e);
	      }
	      return val;
	   }
	

}

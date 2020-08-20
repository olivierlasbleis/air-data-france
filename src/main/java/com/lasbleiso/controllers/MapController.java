package com.lasbleiso.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
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
	
	
	
	
	
	
	@GetMapping("topojson")
	public String obtenirLaListsfrgrrdgvdeDesCommunes() throws Exception {
		JSONObject jsonTopojson = null;
		try {
			
	        File myObj = getFileFromResources("data-topojson.txt");
			//File myObj = new File(
				//	getClass().getClassLoader().getResource("data-topojson.txt").getFile()
				//);
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
	
	// get file from classpath, resources folder
    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }

}

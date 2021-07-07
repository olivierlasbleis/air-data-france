package com.lasbleiso.controllers;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lasbleiso.entities.Departement;
import com.lasbleiso.entities.StationPollution;
import com.lasbleiso.entities.Zone;
import com.lasbleiso.entities.geojson.Geojson;
import com.lasbleiso.entities.vues.DepartementVue;
import com.lasbleiso.services.DepartementPollutionService;

@RestController
@RequestMapping("departements")
public class ValueDepartementPollutionController {

	@Autowired
	DepartementPollutionService departementPollutionService;
	
	@GetMapping("{polluant}")
	public List<DepartementVue> getDepartementsParPolluant(@PathVariable(value="polluant") String polluant) throws JSONException, Exception {
		return departementPollutionService.getAllDepartementsParPolluant(polluant);
	}
	
}

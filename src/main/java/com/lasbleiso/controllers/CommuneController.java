package com.lasbleiso.controllers;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lasbleiso.entities.Commune;
import com.lasbleiso.entities.Zone;
import com.lasbleiso.entities.vues.CommuneVue;
import com.lasbleiso.services.CommuneService;

@RestController
@RequestMapping("communes")
public class CommuneController {
	
	@Autowired
	CommuneService communePollutionService;

	@GetMapping("{polluant}/{codeDepartement}")
	public List<CommuneVue> getValuePollutionCommunes(@PathVariable(value="codeDepartement") String codeDepartement,@PathVariable(value="polluant") String polluant) throws JSONException, Exception {
		return communePollutionService.findByDepartementEtPolluant(codeDepartement,polluant); //departementPollutionService.getGeoJsonDepartements();
	}
	
	
}

package com.lasbleiso.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lasbleiso.entities.vues.SeuilsPollutionVue;
import com.lasbleiso.services.SeuilsPollutionService;

@RestController
@RequestMapping("data")
public class DataController {
	
	@Autowired
	 private Environment env;
	
	@Autowired
	SeuilsPollutionService seuilsPollutionService;
	 
	 @GetMapping("{polluant}")
	 public List<SeuilsPollutionVue> getPropertyValue(@PathVariable(value="polluant") String polluant)
	 {
	  return seuilsPollutionService.getListeSeuilsPollutionVue(polluant);
	 }

}

package com.lasbleiso.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.lasbleiso.entities.vues.SeuilsPollutionVue;

@Service
public class SeuilsPollutionService {
	
	@Autowired
	 private Environment env;

	public List<SeuilsPollutionVue> getListeSeuilsPollutionVue(String polluant){
		
		  String seuils = env.getProperty("seuil." + polluant);
		  String codeCouleurs = env.getProperty("code.couleur");
		  
		  
		  List<String> myListSeuils = new ArrayList<String>(Arrays.asList(seuils.split(",")));
		  List<String> myListcodeCouleurs = new ArrayList<String>(Arrays.asList(codeCouleurs.split(",")));
		  List<SeuilsPollutionVue> listeRetour = new ArrayList<>();
		  for (int i = 0; i < myListSeuils.size(); i++) {
			  listeRetour.add(new SeuilsPollutionVue("< à " + myListSeuils.get(i) + " µg/m³", myListcodeCouleurs.get(i)));
		}
		  
		  return listeRetour;
	}
}

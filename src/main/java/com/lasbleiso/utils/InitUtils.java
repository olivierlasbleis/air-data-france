package com.lasbleiso.utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lasbleiso.entities.Commune;
import com.lasbleiso.entities.MesurePollution;
import com.lasbleiso.entities.StationPollution;
import com.lasbleiso.services.CommuneService;
import com.lasbleiso.services.DepartementPollutionService;
import com.lasbleiso.services.MesurePollutionService;


@Component
public class InitUtils {
	
	@Autowired
	JsonUtils jsonUtils;
	
	@Autowired
	CommuneService communeService;
	
	
	@Autowired
	MesurePollutionService mesurePollutionService;
	
	@Autowired
	DepartementPollutionService departementPollutionService;
	
	public List<MesurePollution> insertionMesurePollution() throws Exception {
		String url =
				"https://public.opendatasoft.com/api/records/1.0/search/?dataset=openaq&rows=10000&sort=measurements_lastupdated&facet=location&facet=measurements_parameter&facet=measurements_sourcename&facet=measurements_lastupdated&geofilter.polygon=(52.0%2C-6)%2C(52%2C9)%2C(38%2C9)%2C(38%2C-6)%2C(52.0%2C-6)";
		/////////////////// OBTENTION DE LA LISTE DES STATIONS DE
		/////////////////// MESURE POLLUTION//////////////////////
		JSONObject myResponse = new JSONObject(ApiUtils.callApi(url));
		List<MesurePollution> listeDeMesurePollution = jsonUtils
				.transformApiRecordsToListMesurePollution(myResponse);
				return listeDeMesurePollution;
		
	}

	public void insertionCommunes() throws Exception {
		String url =
				"https://geo.api.gouv.fr/communes?fields=nom,code,codesPostaux,centre,codeDepartement,population&format=json&geometry=centre";		/////////////////// OBTENTION DE LA LISTE DES STATIONS DE
		/////////////////// MESURE POLLUTION//////////////////////
				JSONArray myResponse = new JSONArray(ApiUtils.callApi(url));
				jsonUtils.transformAndSaveCommunes(myResponse);
	}

	public void moyennesDepartements() {
		
		departementPollutionService.moyennesDepartements();
		
	}
	
	
}

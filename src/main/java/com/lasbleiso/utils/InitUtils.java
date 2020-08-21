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
import com.lasbleiso.entities.ValueCommunePollution;
import com.lasbleiso.services.CommuneService;
import com.lasbleiso.services.MesurePollutionService;
import com.lasbleiso.services.ValueCommunePollutionService;


@Component
public class InitUtils {
	
	@Autowired
	JsonUtils jsonUtils;
	
	@Autowired
	CommuneService communeService;
	
	@Autowired
	ValueCommunePollutionService valueCommunePollutionService;
	
	@Autowired
	MesurePollutionService mesurePollutionService;
	
	public List<MesurePollution> insertionMesurePollution() throws Exception {
		String url =
				"https://public.opendatasoft.com/api/records/1.0/search/?dataset=openaq&rows=10000&sort=measurements_lastupdated&facet=location&facet=measurements_parameter&facet=measurements_sourcename&facet=measurements_lastupdated&geofilter.polygon=(50.0%2C-6)%2C(50%2C-1)%2C(46%2C-1)%2C(46%2C-6)%2C(50.0%2C-6)";
		/////////////////// OBTENTION DE LA LISTE DES STATIONS DE
		/////////////////// MESURE POLLUTION//////////////////////
		JSONObject myResponse = new JSONObject(ApiUtils.callApi(url));
		List<MesurePollution> listeDeMesurePollution = jsonUtils
				.transformApiRecordsToListMesurePollution(myResponse);
				return listeDeMesurePollution;
		
	}

	public List<Commune> insertionCommunes() throws Exception {
		String url =
				"https://geo.api.gouv.fr/communes?codeRegion=53&fields=nom,code,codesPostaux,centre,codeRegion,population&format=json&geometry=centre";		/////////////////// OBTENTION DE LA LISTE DES STATIONS DE
		/////////////////// MESURE POLLUTION//////////////////////
		
				JSONArray myResponse = new JSONArray(ApiUtils.callApi(url));
		List<Commune> listeDeCommune = jsonUtils
				.transformApiRecordsToListCommune(myResponse);
				return listeDeCommune;
		
		
	}
	
	public void insertionValueCommunePollutionPM10() {
		
		List<Commune> listeDesCommunes = communeService.findAll();
		for (Commune commune : listeDesCommunes) {
			if (commune.getStationPollutionPM10() != null) {
			
			Optional<MesurePollution> mesurePollutionOpt = mesurePollutionService.findLastPM10ByIdStationPollution(commune.getStationPollutionPM10().getId());
			MesurePollution mesurePollution= new MesurePollution();
			if (mesurePollutionOpt.isPresent()) {
				mesurePollution = mesurePollutionOpt.get();
			}
			ValueCommunePollution valueCommunePollution =
					new ValueCommunePollution(Integer.parseInt(commune.getCodeCommune()), mesurePollution.getValeur());
			valueCommunePollutionService.saveValueCommunePollution(valueCommunePollution);
			
			}
		}
		 
		
	}

}

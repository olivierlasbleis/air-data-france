package com.lasbleiso.utils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lasbleiso.entities.Commune;
import com.lasbleiso.entities.MesurePollution;
import com.lasbleiso.entities.StationPollution;
import com.lasbleiso.entities.TYPEPOLLUTION;
import com.lasbleiso.entities.ValueCommunePollution;
import com.lasbleiso.services.CommuneService;
import com.lasbleiso.services.MesurePollutionService;
import com.lasbleiso.services.StationPollutionService;


@Component
public class JsonUtils {
	
	@Autowired
	StationPollutionService stationPollutionService;
	
	@Autowired
	MesurePollutionService mesurePollutionService;
	
	@Autowired
	CommuneService communeService;
	
	/**
	 * Cette methode permet de créer les objets StationDeMesurePollution à
	 * partir de la réponse des appels APIs utilisés par l'application
	 * 
	 * @param myResponse
	 * @return
	 * @throws JSONException
	 */
	public List<MesurePollution> transformApiRecordsToListMesurePollution(JSONObject myResponse) {
		
		

			List<MesurePollution> listeMesurePollution = new ArrayList<MesurePollution>();

			int count = myResponse.getJSONArray("records").length();

			for (int i = 0; i < count; i++) { // iterate through jsonArray

				JSONObject jsonObject = myResponse.getJSONArray("records").getJSONObject(i); // get
																								// jsonObject
																								// @
																								// i
																								// position
				String idMesurePollution = jsonObject.getString("recordid");
				String typePollution = jsonObject.getJSONObject("fields").getString("measurements_parameter");
				Double valeur = jsonObject.getJSONObject("fields").getDouble("measurements_value");
				String uniteDeMesure = jsonObject.getJSONObject("fields").getString("measurements_unit");
				ZonedDateTime zonedDateTime = ZonedDateTime. parse(jsonObject.getJSONObject("fields").getString("measurements_lastupdated")) ;
				Double latitude = (Double) jsonObject.getJSONObject("geometry").getJSONArray("coordinates").get(1);
				Double longitude = (Double) jsonObject.getJSONObject("geometry").getJSONArray("coordinates").get(0);
				
				StationPollution stationPollution = stationPollutionService.saveOrNot(new StationPollution(latitude,longitude));
				
				MesurePollution mesurePollution = mesurePollutionService.saveOrNot(new MesurePollution(idMesurePollution, typePollution, valeur, uniteDeMesure, zonedDateTime,stationPollution));
				
				listeMesurePollution.add(mesurePollution);

			}
			return listeMesurePollution;
		
	}

	public List<Commune> transformApiRecordsToListCommune(JSONArray tableauDesCommunes) {
		List<Commune> listeDesCommunes = new ArrayList<Commune>();
		List<StationPollution> listeDesStationPollution = stationPollutionService.findByPolluant("PM10");

		int count = tableauDesCommunes.length();
		for (int i = 0; i < count; i++) { // iterate through jsonArray
			
			Commune commune = new Commune(tableauDesCommunes.getJSONObject(i).getString("code"),
					tableauDesCommunes.getJSONObject(i).getString("nom"),
					tableauDesCommunes.getJSONObject(i).getJSONObject("centre").getJSONArray("coordinates")
					.getDouble(1),
					tableauDesCommunes.getJSONObject(i).getJSONObject("centre").getJSONArray("coordinates")
					.getDouble(0));
			
			//Stream streamListeDesStationsPM10 = stationPollutionService.findByPolluant("PM10").stream;
			//commune.setStationPollutionPM10(determinerStationLaPlusProche());
					
			StationPollution stationPollutionLaPlusProche = new StationPollution(); 
			Double distanceLaPlusCourte = Double.MAX_VALUE;
			for (StationPollution stationPollution : listeDesStationPollution) {
				Double distance = Math.sqrt(stationPollution.getLatitude()- commune.getLatitude())
						+ Math.sqrt(stationPollution.getLongitude()- commune.getLongitude());
				if (distance < distanceLaPlusCourte) {
					distanceLaPlusCourte = distance;
					commune.setStationPollutionPM10(stationPollution);
				}
			}
			communeService.save(commune);
			listeDesCommunes.add(commune);
		}

		return listeDesCommunes;
	}
	
	

	
}

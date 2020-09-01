package com.lasbleiso.utils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lasbleiso.entities.Commune;
import com.lasbleiso.entities.Departement;
import com.lasbleiso.entities.DonneePollution;
import com.lasbleiso.entities.MesurePollution;
import com.lasbleiso.entities.StationPollution;
import com.lasbleiso.services.CommuneService;
import com.lasbleiso.services.DepartementPollutionService;
import com.lasbleiso.services.MesurePollutionService;
import com.lasbleiso.services.StationPollutionService;


@Component
public class JsonUtils {
	
	@Autowired
	StationPollutionService stationPollutionService;
	
	@Autowired
	MesurePollutionService mesurePollutionService;
	
	@Autowired
	DepartementPollutionService departementPollutionService;
	
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

				JSONObject jsonObject = myResponse.getJSONArray("records").getJSONObject(i);
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

	public void transformAndSaveCommunes(JSONArray tableauDesCommunes) throws JSONException, Exception {
		List<StationPollution> listeDeStationPollution = stationPollutionService.findAllStationsAvecMesuresMoinsDeSeptJours();
		
		int count = tableauDesCommunes.length();
		for (int i = 0; i < count; i++) { // iterate through jsonArray
			if (tableauDesCommunes.getJSONObject(i).has("centre")) {
				
				Commune commune = new Commune(tableauDesCommunes.getJSONObject(i).getString("code"),
						tableauDesCommunes.getJSONObject(i).getString("nom"),
						tableauDesCommunes.getJSONObject(i).getJSONObject("centre").getJSONArray("coordinates")
						.getDouble(1),
						tableauDesCommunes.getJSONObject(i).getJSONObject("centre").getJSONArray("coordinates")
						.getDouble(0));
				List<StationPollution> listeDeStationPollutionCourante = new ArrayList<>(listeDeStationPollution);
				listeDeStationPollutionCourante.sort(
						Comparator.comparingDouble(stationPollution -> 
						(stationPollution.getLatitude()- commune.getLatitude()) * (stationPollution.getLatitude()- commune.getLatitude())+ (stationPollution.getLongitude()- commune.getLongitude()) * (stationPollution.getLongitude()- commune.getLongitude())));
				commune.setDepartement(departementPollutionService.findOrCreate(tableauDesCommunes.getJSONObject(i).getString("codeDepartement")));
				int index = 0;
				while (commune.getStationPollutionPM10()==null 
						|| commune.getStationPollutionSO2()==null
						|| commune.getStationPollutionNO2()==null
						|| commune.getStationPollutionCO()==null
						|| commune.getStationPollutionO3()==null
						|| commune.getStationPollutionPM25()==null) {
					for (MesurePollution mesurePollution : mesurePollutionService.findbyStationPollution(listeDeStationPollutionCourante.get(index)) ) {
						if (commune.getStationPollutionPM10()==null && mesurePollution.getTypePollution().equals("PM10")) {
							commune.setStationPollutionPM10(listeDeStationPollutionCourante.get(index));
							
							Optional<List<MesurePollution>> listeMesurePollutionOpt = mesurePollutionService.findLastByIdStationPollutionAndTypePollution(commune.getStationPollutionPM10().getId(), "PM10");
							if (listeMesurePollutionOpt.isPresent()) {
								MesurePollution mesurepollution = mesurePollutionService.findLastByIdStationPollutionAndTypePollution(commune.getStationPollutionPM10().getId(), "PM10").get().get(0);
								commune.setDonneePollutionPM10(new DonneePollution(mesurepollution));
							}
						}else if (commune.getStationPollutionSO2()==null && mesurePollution.getTypePollution().equals("SO2")) {
								commune.setStationPollutionSO2(listeDeStationPollutionCourante.get(index));
								
							Optional<List<MesurePollution>> listeMesurePollutionOpt = mesurePollutionService.findLastByIdStationPollutionAndTypePollution(commune.getStationPollutionSO2().getId(), "SO2");
							if (listeMesurePollutionOpt.isPresent()) {
								MesurePollution mesurepollution = mesurePollutionService.findLastByIdStationPollutionAndTypePollution(commune.getStationPollutionSO2().getId(), "SO2").get().get(0);
								commune.setDonneePollutionSO2(new DonneePollution(mesurepollution));
							}
						}else if (commune.getStationPollutionNO2()==null && mesurePollution.getTypePollution().equals("NO2")) {
							commune.setStationPollutionNO2(listeDeStationPollutionCourante.get(index));
							Optional<List<MesurePollution>> listeMesurePollutionOpt = mesurePollutionService.findLastByIdStationPollutionAndTypePollution(commune.getStationPollutionNO2().getId(), "NO2");
							if (listeMesurePollutionOpt.isPresent()) {
								MesurePollution mesurepollution = mesurePollutionService.findLastByIdStationPollutionAndTypePollution(commune.getStationPollutionNO2().getId(), "NO2").get().get(0);
								commune.setDonneePollutionNO2(new DonneePollution(mesurepollution));
							}
						}else if (commune.getStationPollutionCO()==null && mesurePollution.getTypePollution().equals("CO")) {
							commune.setStationPollutionCO(listeDeStationPollutionCourante.get(index));
							Optional<List<MesurePollution>> listeMesurePollutionOpt = mesurePollutionService.findLastByIdStationPollutionAndTypePollution(commune.getStationPollutionCO().getId(), "CO");
							if (listeMesurePollutionOpt.isPresent()) {
								MesurePollution mesurepollution = mesurePollutionService.findLastByIdStationPollutionAndTypePollution(commune.getStationPollutionCO().getId(), "CO").get().get(0);
								commune.setDonneePollutionCO(new DonneePollution(mesurepollution));
							}
						}else if (commune.getStationPollutionO3()==null && mesurePollution.getTypePollution().equals("O3")) {
							commune.setStationPollutionO3(listeDeStationPollutionCourante.get(index));
							Optional<List<MesurePollution>> listeMesurePollutionOpt = mesurePollutionService.findLastByIdStationPollutionAndTypePollution(commune.getStationPollutionO3().getId(), "O3");
							if (listeMesurePollutionOpt.isPresent()) {
								MesurePollution mesurepollution = mesurePollutionService.findLastByIdStationPollutionAndTypePollution(commune.getStationPollutionO3().getId(), "O3").get().get(0);
								commune.setDonneePollutionO3(new DonneePollution(mesurepollution));
							}
						}else if (commune.getStationPollutionPM25()==null && mesurePollution.getTypePollution().equals("PM2.5")) {
							commune.setStationPollutionPM25(listeDeStationPollutionCourante.get(index));
							Optional<List<MesurePollution>> listeMesurePollutionOpt = mesurePollutionService.findLastByIdStationPollutionAndTypePollution(commune.getStationPollutionPM25().getId(), "PM2.5");
							if (listeMesurePollutionOpt.isPresent()) {
								MesurePollution mesurepollution = mesurePollutionService.findLastByIdStationPollutionAndTypePollution(commune.getStationPollutionPM25().getId(), "PM2.5").get().get(0);
								commune.setDonneePollutionPM25(new DonneePollution(mesurepollution));
							}
						}
					}
					index++;
				}
				communeService.save(commune);
			}
		}

	}
	
	

	
}

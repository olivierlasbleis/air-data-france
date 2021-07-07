package com.lasbleiso.services;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lasbleiso.entities.Commune;
import com.lasbleiso.entities.Departement;
import com.lasbleiso.entities.DonneePollution;
import com.lasbleiso.entities.StationPollution;
import com.lasbleiso.entities.Zone;
import com.lasbleiso.entities.geojson.Coordinate;
import com.lasbleiso.entities.geojson.Feature;
import com.lasbleiso.entities.geojson.Geojson;
import com.lasbleiso.entities.geojson.Geometry;
import com.lasbleiso.entities.geojson.Properties;
import com.lasbleiso.entities.vues.CommuneVue;
import com.lasbleiso.entities.vues.DepartementVue;
import com.lasbleiso.repositories.ZoneRepository;
import com.lasbleiso.utils.ApiUtils;

@Service
public class DepartementPollutionService {
	
	static String url = "https://raw.githubusercontent.com/gregoiredavid/france-geojson/master/departements-version-simplifiee.geojson";
	
	@Autowired
	ZoneRepository departementRepository;
	
	@Autowired
	CommuneService communeService;
	
	@Autowired
	DonneePollutionService donneePollutionService;

 
	public Geojson getGeoJsonDepartements() throws JSONException, Exception {
		
		JSONObject myResponse = new JSONObject(ApiUtils.callApi(url));
		List<Departement> listeDepartements = departementRepository.findAllDepartements();
		
		 //create ObjectMapper instance
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	    //convert json string to object

	    //ICI
	    Properties feasfdvfeture = objectMapper.readValue(myResponse.getJSONArray("features").getJSONObject(0).getJSONObject("properties").toString(),Properties.class);
	    Geometry featdvdure = objectMapper.readValue(myResponse.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").toString(),Geometry.class);
	    Feature feafvture = objectMapper.readValue(myResponse.getJSONArray("features").getJSONObject(0).toString(),Feature.class);
	    Geojson geojson = objectMapper.readValue(myResponse.toString(), Geojson.class); 
		
		JSONArray jsonArrayDepartements = myResponse.getJSONArray("features");
				
		
		return geojson;
	}
	
	public JSONObject getGeoJsonDepartements(String codeDepartement, String nomDepartement) throws JSONException, Exception {
		
		JSONObject myResponse = new JSONObject(ApiUtils.callApi(url));
		List<Departement> listeDepartements = departementRepository.findAllDepartements();
		JSONArray jsonArrayDepartements = myResponse.getJSONArray("features");
		for (int i = 0; i < jsonArrayDepartements.length(); i++) {
			if (jsonArrayDepartements.getJSONObject(i).getJSONObject("properties").getString("code").equals(codeDepartement)) {	
				for (JSONObject geoJsonCommunes : communeService.getListeGeoJsonCommunes(codeDepartement, nomDepartement)) {
					myResponse.getJSONArray("features").put(i, geoJsonCommunes) ;
				}
			}else {
				for (Zone departement : listeDepartements) {
						if ((jsonArrayDepartements.getJSONObject(i).getJSONObject("properties").getString("code").equals(departement.getCode()))) {
							myResponse.getJSONArray("features").getJSONObject(i).getJSONObject("properties")
							.put("dataCO", departement.getDonneePollutionCO());
							myResponse.getJSONArray("features").getJSONObject(i).getJSONObject("properties")
							.put("dataNO2", departement.getDonneePollutionNO2());
							myResponse.getJSONArray("features").getJSONObject(i).getJSONObject("properties")
							.put("dataO3", departement.getDonneePollutionO3());
							myResponse.getJSONArray("features").getJSONObject(i).getJSONObject("properties")
							.put("dataPM10", departement.getDonneePollutionPM10());
							myResponse.getJSONArray("features").getJSONObject(i).getJSONObject("properties")
							.put("dataPM25", departement.getDonneePollutionPM25());
							myResponse.getJSONArray("features").getJSONObject(i).getJSONObject("properties")
							.put("dataSO2", departement.getDonneePollutionSO2());
							break;
						}
					}
				}
			
		}
		return myResponse;
	}

	public Zone findOrCreate(String codeDepartement) throws JSONException, Exception {
		if (departementRepository.findById(codeDepartement).isPresent()) {
			return departementRepository.findById(codeDepartement).get();
		}else {
			JSONObject myResponse = new JSONObject(ApiUtils.callApi("https://geo.api.gouv.fr/departements/" + codeDepartement + "?fields=nom"));
			Departement departement = new Departement(codeDepartement,myResponse.getString("nom"));
			return departementRepository.save(departement);
		}
	}

	public void moyennesDepartements() {
		List<Departement> listeDesDepartements = departementRepository.findAllDepartements();
		for (int i = 0; i < listeDesDepartements.size(); i++) {
			List<Commune> listeDesCommunes = communeService.findByDepartement(listeDesDepartements.get(i).getCode());
			Double sommeCO = 0.0;
			Double sommePM10 = 0.0;
			Double sommePM25 = 0.0;
			Double sommeSO2 = 0.0;
			Double sommeNO2 = 0.0;
			Double sommeO3 = 0.0;
			int nbElements=0;
			for (Commune commune : listeDesCommunes) {
				sommeCO = sommeCO + commune.getDonneePollutionCO().getValeur();
				sommePM10 = sommePM10 + commune.getDonneePollutionPM10().getValeur();
				sommePM25 = sommePM25 + commune.getDonneePollutionPM25().getValeur();
				sommeSO2 = sommeSO2 + commune.getDonneePollutionSO2().getValeur();
				sommeNO2 = sommeNO2 + commune.getDonneePollutionNO2().getValeur();
				sommeO3 = sommeO3 + commune.getDonneePollutionO3().getValeur();
				nbElements++;
			}
			Double moyenneCO = sommeCO / nbElements;
			Double moyennePM10 = sommePM10 / nbElements;
			Double moyennePM25 = sommePM25 / nbElements;
			Double moyenneSO2 = sommeSO2 / nbElements;
			Double moyenneNO2 = sommeNO2 / nbElements;
			Double moyenneO3 = sommeO3 / nbElements;
			listeDesDepartements.get(i).setDonneePollutionCO(
					new DonneePollution(
							"CO",
							moyenneCO,
							listeDesCommunes.get(0).getDonneePollutionCO().getUniteDeMesure(),
					ZonedDateTime.now()
					));
			listeDesDepartements.get(i).setDonneePollutionPM10(
					new DonneePollution(
							"PM10",
							moyennePM10,
							listeDesCommunes.get(0).getDonneePollutionPM10().getUniteDeMesure(),
					ZonedDateTime.now()
					));
			listeDesDepartements.get(i).setDonneePollutionPM25(
					new DonneePollution(
							"PM25",
							moyennePM25,
							listeDesCommunes.get(0).getDonneePollutionPM25().getUniteDeMesure(),
					ZonedDateTime.now()
					));
			listeDesDepartements.get(i).setDonneePollutionO3(
					new DonneePollution(
							"O3",
							moyenneO3,
							listeDesCommunes.get(0).getDonneePollutionO3().getUniteDeMesure(),
					ZonedDateTime.now()
					));
			listeDesDepartements.get(i).setDonneePollutionNO2(
					new DonneePollution(
							"NO2",
							moyenneNO2,
							listeDesCommunes.get(0).getDonneePollutionNO2().getUniteDeMesure(),
					ZonedDateTime.now()
					));
			listeDesDepartements.get(i).setDonneePollutionSO2(
					new DonneePollution(
							"SO2",
							moyenneSO2,
							listeDesCommunes.get(0).getDonneePollutionSO2().getUniteDeMesure(),
					ZonedDateTime.now()
					));
			departementRepository.save(listeDesDepartements.get(i));
		}
		
	}

	public List<Departement> getAllDepartements() {
		
		return departementRepository.findAllDepartements();
	}
	
	public List<DepartementVue> getAllDepartementsParPolluant(String polluant) {
		List<DepartementVue> listeDepartementVue = new ArrayList<DepartementVue>();
		for (Departement departement : departementRepository.findAllDepartements()) {
			listeDepartementVue.add(convertToDepartementVue(departement, polluant));
		}
		return listeDepartementVue;
	}
	
	public List<Zone> getDepartementsPlusCommunes (String codeDepartement) {
		 List<Departement> listeDepartement = departementRepository.findAllDepartements();
		 List<Commune> listeCommunes = communeService.findByDepartement(codeDepartement);
		 
		 
		 List<Zone> listeDepartementZone = listeDepartement
				    .stream()
				    .map(e -> (Zone) e)
				    .collect(Collectors.toList());
		 List<Zone> listeCommuneZone = listeCommunes
				    .stream()
				    .map(e -> (Zone) e)
				    .collect(Collectors.toList());
		 List<Zone> listeZone = new ArrayList<Zone>();
		 listeZone.addAll(listeDepartementZone);
		 listeZone.addAll(listeCommuneZone);
		
		return listeZone;
	 }
	
	public DepartementVue convertToDepartementVue(Departement departement,String polluant) {
		DonneePollution donneePollution = new DonneePollution();
		String indicateurPollution = "";
		StationPollution stationPollution = new StationPollution();
		if (polluant.equals("PM10")) {
			donneePollution = departement.getDonneePollutionPM10();
			indicateurPollution = departement.getIndicateurPollutionPM10();
		}else if (polluant.equals("CO")) {
			donneePollution = departement.getDonneePollutionCO();
			indicateurPollution = departement.getIndicateurPollutionCO();
		}else if (polluant.equals("PM25")) {
			donneePollution = departement.getDonneePollutionPM25();
			indicateurPollution = departement.getIndicateurPollutionPM25();
		}else if (polluant.equals("SO2")) {
			donneePollution = departement.getDonneePollutionSO2();
			indicateurPollution = departement.getIndicateurPollutionSO2();
		}else if (polluant.equals("NO2")) {
			donneePollution = departement.getDonneePollutionNO2();
			indicateurPollution = departement.getIndicateurPollutionNO2();
		}else if (polluant.equals("O3")) {
			donneePollution = departement.getDonneePollutionO3();
			indicateurPollution = departement.getIndicateurPollutionO3();
		}
		return new DepartementVue(departement.getCode(), 
								departement.getNom(),
								donneePollutionService.convertToDonneePollutionVue(donneePollution),
								indicateurPollution
								);
	}

}

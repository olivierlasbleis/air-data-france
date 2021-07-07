package com.lasbleiso.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lasbleiso.entities.Commune;
import com.lasbleiso.entities.Departement;
import com.lasbleiso.entities.DonneePollution;
import com.lasbleiso.entities.StationPollution;
import com.lasbleiso.entities.Zone;
import com.lasbleiso.entities.vues.CommuneVue;
import com.lasbleiso.entities.vues.DepartementVue;
import com.lasbleiso.repositories.ZoneRepository;
import com.lasbleiso.utils.ApiUtils;

@Service
public class CommuneService {
	
	@Autowired
	ZoneRepository communeRepository;
	
	@Autowired
	DonneePollutionService donneePollutionService;

	public Commune save(Commune commune) {
		return communeRepository.save(commune);
		
	}

	public List<Zone> findAll() {
		return communeRepository.findAll();
	}
	
	public List<JSONObject> getListeGeoJsonCommunes(String codeDepartement, String nomDepartement) throws JSONException, Exception {
		
		List<JSONObject> listeGeoJsonCommunes = new ArrayList<>();
		String url =
				"https://raw.githubusercontent.com/gregoiredavid/france-geojson/master/departements/" 
		+ codeDepartement + "-" + nomDepartement 
		+ "/communes-"+ codeDepartement +"-"+ nomDepartement +".geojson";
		JSONObject myResponse = new JSONObject(ApiUtils.callApi(url));
		List<Commune> listeCommunes = findByDepartement(codeDepartement);
		JSONArray jsonArrayCommunes = myResponse.getJSONArray("features");
				
		for (int i = 0; i < jsonArrayCommunes.length(); i++) {
				for (Commune commune : listeCommunes) {
						if ((jsonArrayCommunes.getJSONObject(i).getJSONObject("properties").getString("code").equals(commune.getCodeCommune()))) {
							JSONObject jSONObjectCommune = myResponse.getJSONArray("features").getJSONObject(i);
							jSONObjectCommune.getJSONObject("properties").put("dataCO", commune.getDonneePollutionCO())
							.put("dataCO", commune.getDonneePollutionCO())
							.put("dataNO2", commune.getDonneePollutionNO2())
							.put("dataO3", commune.getDonneePollutionO3())
							.put("dataPM10", commune.getDonneePollutionPM10())
							.put("dataPM25", commune.getDonneePollutionPM25())
							.put("dataSO2", commune.getDonneePollutionSO2());
							listeGeoJsonCommunes.add(jSONObjectCommune);
							break;
						}
					}
				}
		return listeGeoJsonCommunes;
		
	}

	public List<Commune> findByDepartement(String codeDepartement) {
		Optional<List<Commune>> listeCommunesOpt = communeRepository.findByCodeDepartement(codeDepartement);
		List<Commune> listeCommunes = new ArrayList<Commune>();
		if (listeCommunesOpt.isPresent()) {
			return listeCommunes = listeCommunesOpt.get();
		}else {
			return listeCommunes;
		}
		
	}
	
	public List<CommuneVue> findByDepartementEtPolluant(String codeDepartement, String polluant) {
		
		
			Stream<CommuneVue> streamCommuneVue = 
					findByDepartement(codeDepartement).stream()
																.map(x -> convertToCommuneVue(x, polluant));
			
			return streamCommuneVue.collect(Collectors.toList());
		}
		
	public CommuneVue convertToCommuneVue(Commune commune,String polluant) {
		DonneePollution donneePollution = new DonneePollution();
		String indicateurPollution = "";
		StationPollution stationPollution = new StationPollution();
		if (polluant.equals("PM10")) {
			donneePollution = commune.getDonneePollutionPM10();
			indicateurPollution = commune.getIndicateurPollutionPM10();
			stationPollution = commune.getStationPollutionPM10();
		}else if (polluant.equals("CO")) {
			donneePollution = commune.getDonneePollutionCO();
			indicateurPollution = commune.getIndicateurPollutionCO();
			stationPollution = commune.getStationPollutionCO();
		}else if (polluant.equals("PM25")) {
			donneePollution = commune.getDonneePollutionPM25();
			indicateurPollution = commune.getIndicateurPollutionPM25();
			stationPollution = commune.getStationPollutionPM25();
		}else if (polluant.equals("SO2")) {
			donneePollution = commune.getDonneePollutionSO2();
			indicateurPollution = commune.getIndicateurPollutionSO2();
			stationPollution = commune.getStationPollutionSO2();
		}else if (polluant.equals("NO2")) {
			donneePollution = commune.getDonneePollutionNO2();
			indicateurPollution = commune.getIndicateurPollutionNO2();
			stationPollution = commune.getStationPollutionNO2();
		}else if (polluant.equals("O3")) {
			donneePollution = commune.getDonneePollutionO3();
			indicateurPollution = commune.getIndicateurPollutionO3();
			stationPollution = commune.getStationPollutionO3();
		}
		return new CommuneVue(commune.getCode(), 
								commune.getNom(),
								donneePollutionService.convertToDonneePollutionVue(donneePollution),
								indicateurPollution,
								stationPollution);
	}
	
}

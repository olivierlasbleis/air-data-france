package com.lasbleiso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lasbleiso.entities.StationPollution;
import com.lasbleiso.services.StationPollutionService;

@RestController
@RequestMapping("station-pollutions")
public class StationPollutionController {

	@Autowired
	StationPollutionService stationPollutionService;
	
	
	@GetMapping("All/{typePollution}")
	public List<StationPollution> getListStationPollution(@PathVariable(value="typePollution") String typePollution) {
		if (typePollution.equals("PM10")) {
			return stationPollutionService.findAllPM10();
		}else if (typePollution.equals("CO")) {
			return stationPollutionService.findAllCO();
		}else if (typePollution.equals("NO2")) {
			return stationPollutionService.findAllNO2();
		}else if (typePollution.equals("O3")) {
			return stationPollutionService.findAllO3();
		}else if (typePollution.equals("PM25")) {
			return stationPollutionService.findAllPM25();
		}else if (typePollution.equals("SO2")) {
			return stationPollutionService.findAllSO2();
		}else {
			return null;
		}
	}
}

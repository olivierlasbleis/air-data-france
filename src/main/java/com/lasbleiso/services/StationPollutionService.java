package com.lasbleiso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lasbleiso.entities.StationPollution;
import com.lasbleiso.repositories.StationPollutionRepository;

@Service
public class StationPollutionService {
	
	@Autowired
	StationPollutionRepository stationPollutionRepository;

	

	public StationPollution saveOrNot(StationPollution stationPollution) {
		if (stationPollutionRepository.findByLatitudeAndLongitude(stationPollution.getLatitude(), stationPollution.getLongitude()).isPresent()) {
			return stationPollutionRepository.findByLatitudeAndLongitude(stationPollution.getLatitude(), stationPollution.getLongitude()).get();
		}else {
			return stationPollutionRepository.save(stationPollution);
		}
		
	}



	public List<StationPollution> findAllStationsAvecMesuresMoinsDeSeptJours() {
		// TODO Auto-generated method stub
		return stationPollutionRepository.findAllStationsAvecMesuresMoinsDeSeptJours();
	}



	public List<StationPollution> findByPolluant(String typePollution) {
		// TODO Auto-generated method stub
		return stationPollutionRepository.findByPolluant(typePollution);
	}



	public List<StationPollution> findAllPM10() {
		return stationPollutionRepository.findAllPM10();
	}



	public List<StationPollution> findAllCO() {
		return stationPollutionRepository.findAllCO();
	}



	public List<StationPollution> findAllNO2() {
		return stationPollutionRepository.findAllNO2();
	}



	public List<StationPollution> findAllO3() {
		return stationPollutionRepository.findAllO3();
	}



	public List<StationPollution> findAllPM25() {
		return stationPollutionRepository.findAllPM25();
	}



	public List<StationPollution> findAllSO2() {
		return stationPollutionRepository.findAllSO2();
	}



	

	
}

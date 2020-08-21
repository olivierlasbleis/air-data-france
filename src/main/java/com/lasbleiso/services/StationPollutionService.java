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



	public List<StationPollution> findAll() {
		// TODO Auto-generated method stub
		return stationPollutionRepository.findAll();
	}



	public List<StationPollution> findByPolluant(String typePollution) {
		// TODO Auto-generated method stub
		return stationPollutionRepository.findByPolluant(typePollution);
	}

	
}

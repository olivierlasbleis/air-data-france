package com.lasbleiso.services;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.lasbleiso.entities.MesurePollution;
import com.lasbleiso.entities.StationPollution;
import com.lasbleiso.repositories.MesurePollutionRepository;


@Service
public class MesurePollutionService {
	
	@Autowired
	MesurePollutionRepository mesurePollutionRepository;
	
	public boolean mesurePollutionExistOrNot(String idMesurePollution) {
		
		if (mesurePollutionRepository.findById(idMesurePollution).isPresent()) {
			return true;
		}else {
			return false;
		}
		
		
		
	}

	public MesurePollution saveOrNot(MesurePollution mesurePollution) {
		if (mesurePollutionRepository.findById(mesurePollution.getId()).isPresent()) {
			return mesurePollution;
		}else {
			ZonedDateTime dateTimeMinusSeven = ZonedDateTime.now().minus(12,ChronoUnit.DAYS);
			ZonedDateTime dateMesure = mesurePollution.getDate();
			if (dateMesure.compareTo(dateTimeMinusSeven) > 0) {
				return mesurePollutionRepository.save(mesurePollution);
			}else {
				return mesurePollution;
			}
			
		}
	}

	
	public Optional<List<MesurePollution>> findLastByIdStationPollutionAndTypePollution(Integer id, String typePollution) {
		// TODO Auto-generated method stub
		return mesurePollutionRepository.findLastByIdStationPollutionAndTypePollution(id,typePollution);
	}
	
	public List<MesurePollution> findbyStationPollution(StationPollution stationPollution) {
		
		return mesurePollutionRepository.findbyStationPollution(stationPollution.getId());
	}

	public List<MesurePollution> findAll() {
		// TODO Auto-generated method stub
		return mesurePollutionRepository.findAll();
	}

}

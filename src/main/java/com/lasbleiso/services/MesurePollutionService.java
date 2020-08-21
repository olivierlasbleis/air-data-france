package com.lasbleiso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.lasbleiso.entities.MesurePollution;
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
			return mesurePollutionRepository.save(mesurePollution);
		}
	}

	
	public Optional<MesurePollution> findLastPM10ByIdStationPollution(Integer id) {
		// TODO Auto-generated method stub
		return mesurePollutionRepository.findLastPM10ByIdStationPollution(id);
	}

}

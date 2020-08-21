package com.lasbleiso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lasbleiso.entities.ValueCommunePollution;
import com.lasbleiso.repositories.ValueCommunePollutionRepository;

@Service
public class ValueCommunePollutionService {

	@Autowired
	ValueCommunePollutionRepository valueCommunePollutionRepository;
	
	public ValueCommunePollution saveValueCommunePollution(ValueCommunePollution valueCommunePollution) {
		
		return valueCommunePollutionRepository.save(valueCommunePollution);
		
	}
}

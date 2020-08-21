package com.lasbleiso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lasbleiso.entities.Commune;
import com.lasbleiso.repositories.CommuneRepository;

@Service
public class CommuneService {
	
	@Autowired
	CommuneRepository communeRepository;

	public Commune save(Commune commune) {
		return communeRepository.save(commune);
		
	}

	public List<Commune> findAll() {
		return communeRepository.findAll();
	}
}

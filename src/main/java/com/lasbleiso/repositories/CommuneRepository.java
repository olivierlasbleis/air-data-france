package com.lasbleiso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lasbleiso.entities.Commune;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, String>{

	
	
}

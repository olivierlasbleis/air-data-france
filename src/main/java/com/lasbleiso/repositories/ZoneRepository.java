package com.lasbleiso.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lasbleiso.entities.Commune;
import com.lasbleiso.entities.Departement;
import com.lasbleiso.entities.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, String>{

	@Query(value = "SELECT * FROM ZONE c WHERE c.Departement_code_Departement = :codeDepartement", 
			  nativeQuery = true)
	public Optional<List<Commune>> findByCodeDepartement(@Param("codeDepartement")  String codeDepartement);

	
	@Query(value = "SELECT * FROM Zone where DTYPE='Commune'", 
			  nativeQuery = true)
	public List<Commune> findAllCommunes();
	

	@Query(value = "SELECT * FROM Zone where DTYPE='Departement'", 
			  nativeQuery = true)
	public List<Departement> findAllDepartements();

	
}

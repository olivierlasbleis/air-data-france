package com.lasbleiso.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lasbleiso.entities.MesurePollution;
import com.lasbleiso.entities.StationPollution;

@Repository
public interface MesurePollutionRepository extends JpaRepository<MesurePollution, String>{

	/**
	 * Cette methode retourne une MesurePollution présente en base de donnée en
	 * fonction de son id
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	public Optional<MesurePollution> findById(String id);

	@Query(value = "SELECT * FROM MESURE_POLLUTION m where m.TYPE_POLLUTION = :typePollution AND m.StATION_POLLUTION_ID = :id AND m.daTE = (SELECT MAX(DATE) FROM MESURE_POLLUTION mm where mm.TYPE_POLLUTION = :typePollution AND mm.StATION_POLLUTION_ID = :id)", 
			  nativeQuery = true)
	public Optional<List<MesurePollution>> findLastByIdStationPollutionAndTypePollution(@Param("id") Integer id, @Param("typePollution") String typePollution);

	@Query(value = "SELECT * FROM MESURE_POLLUTION m where m.StATION_POLLUTION_ID = :idStationPollution", 
			  nativeQuery = true)
	public List<MesurePollution> findbyStationPollution(@Param("idStationPollution") Integer idStationPollution);
}

package com.lasbleiso.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lasbleiso.entities.MesurePollution;
import com.lasbleiso.entities.StationPollution;

public interface StationPollutionRepository extends JpaRepository<StationPollution, Integer>{

	public Optional<StationPollution> findByLatitudeAndLongitude(Double latitude, Double longitude);

	@Query(value = "SELECT * FROM STATION_POLLUTION where ID IN (\r\n" + 
			"select StATION_POLLUTION_ID from MESURE_POLLUTION where TYPE_POLLUTION = :typePollution)", 
			  nativeQuery = true)
	public List<StationPollution> findByPolluant(@Param("typePollution") String typePollution);
	
}

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

	@Query(value = "SELECT * FROM STATION_POLLUTION st  where st.ID IN (\r\n" + 
			"select distinct(STATION_POLLUTION_ID) from mesure_pollution where DATE  >  now() -7\r\n" + 
			")"
			, 
			  nativeQuery = true)
	public List<StationPollution> findAllStationsAvecMesuresMoinsDeSeptJours();
	
	@Query(value = "SELECT * FROM STATION_POLLUTION a where a.ID IN \r\n"
			 + "(select StATION_POLLUTION_ID from MESURE_POLLUTION b where b.TYPE_POLLUTION = :typePollution) \r\n"
			, 
			  nativeQuery = true)
	public List<StationPollution> findByPolluant(@Param("typePollution") String typePollution);
	
	@Query(value = "SELECT * FROM STATION_POLLUTION a where a.ID IN \r\n"
			 + "(select StATION_POLLUTION_ID from MESURE_POLLUTION b where b.TYPE_POLLUTION = 'PM10') \r\n"
			+ " and a.ID IN (select c.Station_PollutionPM10_id from COMMUNE c)", 
			  nativeQuery = true)
	public List<StationPollution> findAllPM10();
	
	@Query(value = "SELECT * FROM STATION_POLLUTION a where a.ID IN \r\n"
			 + "(select StATION_POLLUTION_ID from MESURE_POLLUTION b where b.TYPE_POLLUTION = 'SO2') \r\n"
			+ " and a.ID IN (select c.Station_PollutionSO2_id from COMMUNE c)", 
			  nativeQuery = true)
	public List<StationPollution> findAllSO2();
	
	@Query(value = "SELECT * FROM STATION_POLLUTION a where a.ID IN \r\n"
			 + "(select StATION_POLLUTION_ID from MESURE_POLLUTION b where b.TYPE_POLLUTION = 'CO') \r\n"
			+ " and a.ID IN (select c.Station_PollutionCO_id from COMMUNE c)", 
			  nativeQuery = true)
	public List<StationPollution> findAllCO();
	
	@Query(value = "SELECT * FROM STATION_POLLUTION a where a.ID IN \r\n"
			 + "(select StATION_POLLUTION_ID from MESURE_POLLUTION b where b.TYPE_POLLUTION = 'O3') \r\n"
			+ " and a.ID IN (select c.Station_PollutionO3_id from COMMUNE c)", 
			  nativeQuery = true)
	public List<StationPollution> findAllO3();
	
	@Query(value = "SELECT * FROM STATION_POLLUTION a where a.ID IN \r\n"
			 + "(select StATION_POLLUTION_ID from MESURE_POLLUTION b where b.TYPE_POLLUTION = 'NO2') \r\n"
			+ " and a.ID IN (select c.Station_PollutionNO2_id from COMMUNE c)", 
			  nativeQuery = true)
	public List<StationPollution> findAllNO2();
	
	@Query(value = "SELECT * FROM STATION_POLLUTION a where a.ID IN \r\n"
			 + "(select StATION_POLLUTION_ID from MESURE_POLLUTION b where b.TYPE_POLLUTION = 'PM25') \r\n"
			+ " and a.ID IN (select c.Station_PollutionPM25_id from COMMUNE c)", 
			  nativeQuery = true)
	public List<StationPollution> findAllPM25();
	
}

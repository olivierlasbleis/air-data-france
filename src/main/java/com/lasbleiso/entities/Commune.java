package com.lasbleiso.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objet Commune représentant une commune
 * 
 * @author Diginamic02
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commune implements Serializable {

	/**
	 * codeCommune : String est le code insee de la commune, un code insee est
	 * attribué par l'état à chaque commune et est unique à chaque commune en
	 * France (contrairement au code postal), exemple: code insee de nantes:
	 * 44109
	 */
	@Id
	private String codeCommune;
	/** nom : String est le nom de la commune (exemple: Nantes) */
	private String nom;
	/** latitude : Double est la latitude de la commune */
	private Double latitude;
	/** longitude : Double est la longitude de la commune */
	private Double longitude;

	/**
	 * stationDeMesureMeteo : StationDeMesureMeteo est la station de mesure
	 * Meteo la plus proche de la commune
	 */
	@ManyToOne
	@JoinColumn(name = "StationPollutionPM10_id")
	StationPollution stationPollutionPM10;
	
	/**
	 * stationDeMesureSO2 : StationDeMesurePollution est la station de mesure
	 * pollution la plus proche de la commune et mesurant le SO2 dans l'air
	 */
	@ManyToOne
	@JoinColumn(name = "StationPollutionSO2_id")
	StationPollution stationPollutionSO2;

	/**
	 * stationDeMesurePM25 : StationDeMesurePollution est la station de mesure
	 * pollution la plus proche de la commune et mesurant le PM2.5 dans l'air
	 */
	@ManyToOne
	@JoinColumn(name = "StationPollutionPM25_id")
	StationPollution stationPollutionPM25;

	

	/**
	 * stationDeMesureO3 : StationDeMesurePollution est la station de mesure
	 * pollution la plus proche de la commune et mesurant le O3 dans l'air
	 */
	@ManyToOne
	@JoinColumn(name = "StationPollutionO3_id")
	StationPollution stationPollutionO3;

	/**
	 * stationDeMesureNO2 : StationDeMesurePollution est la station de mesure
	 * pollution la plus proche de la commune et mesurant le NO2 dans l'air
	 */
	@ManyToOne
	@JoinColumn(name = "StationPollutionNO2_id")
	StationPollution stationPollutionNO2;

	/**
	 * stationDeMesureCO : StationDeMesurePollution est la station de mesure
	 * pollution la plus proche de la commune et mesurant le CO dans l'air
	 */
	@ManyToOne
	@JoinColumn(name = "StationPollutionCO_id")
	StationPollution stationPollutionCO;

	
	public Commune() {
		super();
	}	
		
	public Commune(String codeCommune, String nom, Double latitude, Double longitude) {
		super();
		this.codeCommune = codeCommune;
		this.nom = nom;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getCodeCommune() {
		return codeCommune;
	}

	public void setCodeCommune(String codeCommune) {
		this.codeCommune = codeCommune;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	

	public StationPollution getStationPollutionSO2() {
		return stationPollutionSO2;
	}

	public void setStationPollutionSO2(StationPollution stationPollutionSO2) {
		this.stationPollutionSO2 = stationPollutionSO2;
	}

	public StationPollution getStationPollutionPM25() {
		return stationPollutionPM25;
	}

	public void setStationPollutionPM25(StationPollution stationPollutionPM25) {
		this.stationPollutionPM25 = stationPollutionPM25;
	}

	public StationPollution getStationPollutionPM10() {
		return stationPollutionPM10;
	}

	public void setStationPollutionPM10(StationPollution stationPollutionPM10) {
		this.stationPollutionPM10 = stationPollutionPM10;
	}

	public StationPollution getStationPollutionO3() {
		return stationPollutionO3;
	}

	public void setStationPollutionO3(StationPollution stationPollutionO3) {
		this.stationPollutionO3 = stationPollutionO3;
	}

	public StationPollution getStationPollutionNO2() {
		return stationPollutionNO2;
	}

	public void setStationPollutionNO2(StationPollution stationPollutionNO2) {
		this.stationPollutionNO2 = stationPollutionNO2;
	}

	public StationPollution getStationPollutionCO() {
		return stationPollutionCO;
	}

	public void setStationPollutionCO(StationPollution stationPollutionCO) {
		this.stationPollutionCO = stationPollutionCO;
	}

	
}

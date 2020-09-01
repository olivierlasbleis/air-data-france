package com.lasbleiso.entities;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesurePollution {

	/**
	 * id de la mesure de pollution dans la base de données
	 */
	@Id
	private String id;
	
	/**
	 * nom du polluant
	 */
	private String typePollution;
	/**
	 * valeur de la mesure de pollution
	 */
	private Double valeur;
	/**
	 * valeur de la mesure de pollution
	 */
	private String uniteDeMesure;
	/**
	 * date de la mesure
	 */
	private ZonedDateTime date;
	/**
	 * la station de mesure (qui a enregistré la donnée)
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationPollution_id")
	private StationPollution stationPollution;
	
	public MesurePollution() {
		super();
	}
	public MesurePollution(String id, String typePollution, Double valeur, String uniteDeMesure, ZonedDateTime date,
			StationPollution stationPollution) {
		super();
		this.id = id;
		this.typePollution = typePollution;
		this.valeur = valeur;
		this.uniteDeMesure = uniteDeMesure;
		this.date = date;
		this.stationPollution = stationPollution;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTypePollution() {
		return typePollution;
	}


	public void setTypePollution(String typePollution) {
		this.typePollution = typePollution;
	}


	public Double getValeur() {
		return valeur;
	}


	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}


	public String getUniteDeMesure() {
		return uniteDeMesure;
	}


	public void setUniteDeMesure(String uniteDeMesure) {
		this.uniteDeMesure = uniteDeMesure;
	}


	public ZonedDateTime getDate() {
		return date;
	}


	public void setDate(ZonedDateTime date) {
		this.date = date;
	}


	public StationPollution getStationPollution() {
		return stationPollution;
	}


	public void setStationPollution(StationPollution stationPollution) {
		this.stationPollution = stationPollution;
	}


}

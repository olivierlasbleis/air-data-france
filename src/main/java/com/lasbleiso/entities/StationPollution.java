package com.lasbleiso.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StationPollution {

	
	/**
	 * id de la station en base de données
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * latitude de la station
	 */
	private Double latitude;
	/**
	 * longitude de la station
	 */
	private Double longitude;
	/**
	 * dernière mesure du pollant cité
	 */
	@ManyToOne
	@JoinColumn(name = "MesureSO2_id")
	private MesurePollution mesureSO2;
	/**
	 * dernière mesure du pollant cité
	 */
	@ManyToOne
	@JoinColumn(name = "MesureNO2_id")
	private MesurePollution mesureNO2;
	/**
	 * dernière mesure du pollant cité
	 */
	@ManyToOne
	@JoinColumn(name = "MesurePM10_id")
	private MesurePollution mesurePM10;
	/**
	 * dernière mesure du pollant cité
	 */
	@ManyToOne
	@JoinColumn(name = "MesurePM25_id")
	private MesurePollution mesurePM25;
	/**
	 * dernière mesure du pollant cité
	 */
	@ManyToOne
	@JoinColumn(name = "MesureCO_id")
	private MesurePollution mesureCO;
	/**
	 * dernière mesure du pollant cité
	 */
	@ManyToOne
	@JoinColumn(name = "MesureO3_id")
	private MesurePollution mesureO3;
	
	
	public StationPollution(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public StationPollution() {
		super();
	}
}

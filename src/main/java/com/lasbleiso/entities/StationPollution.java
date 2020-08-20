package com.lasbleiso.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}

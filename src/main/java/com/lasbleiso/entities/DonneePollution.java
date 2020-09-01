package com.lasbleiso.entities;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DonneePollution {
	
	/**
	 * id de la mesure de pollution dans la base de données
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
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
	private ZonedDateTime dateDeMesure;
	/**
	 * date de la mesure
	 */
	private ZonedDateTime dateDeReference;
	
	public DonneePollution() {
		super();
	}
	
	public DonneePollution(MesurePollution mesurepollution) {
		super();
		this.typePollution = mesurepollution.getTypePollution();
		this.valeur = mesurepollution.getValeur();
		this.uniteDeMesure = mesurepollution.getUniteDeMesure();
		this.dateDeMesure = mesurepollution.getDate();
		this.dateDeReference = ZonedDateTime.now();
	}
	
	
	public DonneePollution(String typePollution, Double valeur, String uniteDeMesure, ZonedDateTime dateDeMesure,
			ZonedDateTime dateDeReference) {
		super();
		this.typePollution = typePollution;
		this.valeur = valeur;
		this.uniteDeMesure = uniteDeMesure;
		this.dateDeMesure = dateDeMesure;
		this.dateDeReference = dateDeReference;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public ZonedDateTime getDateDeMesure() {
		return dateDeMesure;
	}

	public void setDateDeMesure(ZonedDateTime dateDeMesure) {
		this.dateDeMesure = dateDeMesure;
	}

	public ZonedDateTime getDateDeReference() {
		return dateDeReference;
	}

	public void setDateDeReference(ZonedDateTime dateDeReference) {
		this.dateDeReference = dateDeReference;
	}

	public DonneePollution(String typePollution, Double valeur, String uniteDeMesure, ZonedDateTime dateDeReference) {
		super();
		this.typePollution = typePollution;
		this.valeur = valeur;
		this.uniteDeMesure = uniteDeMesure;
		this.dateDeReference = dateDeReference;
	}

}

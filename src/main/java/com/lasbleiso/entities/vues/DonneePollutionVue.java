package com.lasbleiso.entities.vues;

import java.time.ZonedDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class DonneePollutionVue {

	
	private String typePollution;

	private Double valeur;

	private String uniteDeMesure;

	private String dateDeMesure;

	private String ageDeLaMesure;

	public DonneePollutionVue(String typePollution, Double valeur, String uniteDeMesure, String dateDeMesure,
			String ageDeLaMesure) {
		super();
		this.typePollution = typePollution;
		this.valeur = valeur;
		this.uniteDeMesure = uniteDeMesure;
		this.dateDeMesure = dateDeMesure;
		this.ageDeLaMesure = ageDeLaMesure;
	}

	public DonneePollutionVue() {
		super();
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

	public String getDateDeMesure() {
		return dateDeMesure;
	}

	public void setDateDeMesure(String dateDeMesure) {
		this.dateDeMesure = dateDeMesure;
	}

	public String getAgeDeLaMesure() {
		return ageDeLaMesure;
	}

	public void setAgeDeLaMesure(String ageDeLaMesure) {
		this.ageDeLaMesure = ageDeLaMesure;
	}
}

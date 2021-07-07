package com.lasbleiso.entities.geojson;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lasbleiso.entities.DonneePollution;

public class Properties {
	private String code;
	private String nom;
	DonneePollution donneePollutionPM10;
	DonneePollution donneePollutionSO2;
	DonneePollution donneePollutionPM25;
	DonneePollution donneePollutionO3;
	DonneePollution donneePollutionNO2;
	DonneePollution donneePollutionCO;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public DonneePollution getDonneePollutionPM10() {
		return donneePollutionPM10;
	}
	public void setDonneePollutionPM10(DonneePollution donneePollutionPM10) {
		this.donneePollutionPM10 = donneePollutionPM10;
	}
	public DonneePollution getDonneePollutionSO2() {
		return donneePollutionSO2;
	}
	public void setDonneePollutionSO2(DonneePollution donneePollutionSO2) {
		this.donneePollutionSO2 = donneePollutionSO2;
	}
	public DonneePollution getDonneePollutionPM25() {
		return donneePollutionPM25;
	}
	public void setDonneePollutionPM25(DonneePollution donneePollutionPM25) {
		this.donneePollutionPM25 = donneePollutionPM25;
	}
	public DonneePollution getDonneePollutionO3() {
		return donneePollutionO3;
	}
	public void setDonneePollutionO3(DonneePollution donneePollutionO3) {
		this.donneePollutionO3 = donneePollutionO3;
	}
	public DonneePollution getDonneePollutionNO2() {
		return donneePollutionNO2;
	}
	public void setDonneePollutionNO2(DonneePollution donneePollutionNO2) {
		this.donneePollutionNO2 = donneePollutionNO2;
	}
	public DonneePollution getDonneePollutionCO() {
		return donneePollutionCO;
	}
	public void setDonneePollutionCO(DonneePollution donneePollutionCO) {
		this.donneePollutionCO = donneePollutionCO;
	}
}

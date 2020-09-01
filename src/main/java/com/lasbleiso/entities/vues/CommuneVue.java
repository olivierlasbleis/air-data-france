package com.lasbleiso.entities.vues;

import com.lasbleiso.entities.Commune;
import com.lasbleiso.entities.Departement;
import com.lasbleiso.entities.DonneePollution;
import com.lasbleiso.entities.StationPollution;

public class CommuneVue {
	
	protected String code;
	protected String nom;
	DonneePollutionVue donneePollution;
	String indicateurPollution;
	StationPollution stationPollution;
	
	
	public CommuneVue(String code, String nom, DonneePollutionVue donneePollution, String indicateurPollution,
			StationPollution stationPollution) {
		super();
		this.code = code;
		this.nom = nom;
		this.donneePollution = donneePollution;
		this.indicateurPollution = indicateurPollution;
		this.stationPollution = stationPollution;
	}


	public CommuneVue() {
		super();
	}


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


	public DonneePollutionVue getDonneePollution() {
		return donneePollution;
	}


	public void setDonneePollution(DonneePollutionVue donneePollution) {
		this.donneePollution = donneePollution;
	}


	public String getIndicateurPollution() {
		return indicateurPollution;
	}


	public void setIndicateurPollution(String indicateurPollution) {
		this.indicateurPollution = indicateurPollution;
	}


	public StationPollution getStationPollution() {
		return stationPollution;
	}


	public void setStationPollution(StationPollution stationPollution) {
		this.stationPollution = stationPollution;
	}

}

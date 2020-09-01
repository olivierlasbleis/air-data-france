package com.lasbleiso.entities.vues;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lasbleiso.entities.Departement;
import com.lasbleiso.entities.DonneePollution;

public class DepartementVue {
	
	
	protected String code;
	protected String nom;
	DonneePollutionVue donneePollution;
	String indicateurPollution;
	
	public DepartementVue(String code, String nom, DonneePollutionVue donneePollution, String indicateurPollution) {
		super();
		this.code = code;
		this.nom = nom;
		this.donneePollution = donneePollution;
		this.indicateurPollution = indicateurPollution;
	}

	public DepartementVue() {
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
	
	

}

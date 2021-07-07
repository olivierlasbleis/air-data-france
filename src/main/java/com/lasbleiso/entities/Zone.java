package com.lasbleiso.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Zone {

	private static Double[] seuilPM10 = {6.0,13.0,20.0,27.0,34.0,41.0,49.0,64.0,79.0};
	private static Double[] seuilPM25 = {7.5,10.0,14.0,17.5,21.0,24.5,28.0,31.5,35.0};
	private static Double[] seuilO3 = {29.0,54.0,79.0,104.0,129.0,149.0,179.0,209.0,240.0};
	private static Double[] seuilNO2 = {29.0,54.0,84.0,109.0,134.0,164.0,199.0,274.0,399.0};
	private static Double[] seuilSO2 = {39.0,79.0,119.0,159.0,199.0,249.0,299.0,399.0,499.0};
	private static Double[] seuilCO = {0.05,0.1,0.15,0.20,0.25,0.30,0.35,0.40,0.45};
	
	/**
	 * codeCommune : String est le code insee de la commune, un code insee est
	 * attribué par l'état à chaque commune et est unique à chaque commune en
	 * France (contrairement au code postal), exemple: code insee de nantes:
	 * 44109
	 */
	@Id
	protected String code;
	/** nom : String est le nom de la commune (exemple: Nantes) */
	protected String nom;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DonneePollutionPM10_id")
	DonneePollution donneePollutionPM10;
	String indicateurPollutionPM10;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DonneePollutionSO2_id")
	DonneePollution donneePollutionSO2;
	String indicateurPollutionSO2;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DonneePollutionPM25_id")
	DonneePollution donneePollutionPM25;
	String indicateurPollutionPM25;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DonneePollutionO3_id")
	DonneePollution donneePollutionO3;
	String indicateurPollutionO3;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DonneePollutionNO2_id")
	DonneePollution donneePollutionNO2;
	String indicateurPollutionNO2;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DonneePollutionCO_id")
	DonneePollution donneePollutionCO;
	String indicateurPollutionCO;
	
	public Zone() {
		super();
	}
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String codeDepartement) {
		this.code = codeDepartement;
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
		this.indicateurPollutionPM10 = determinerIndicateur("PM10");
	}

	

	public DonneePollution getDonneePollutionSO2() {
		return donneePollutionSO2;
	}

	public void setDonneePollutionSO2(DonneePollution donneePollutionSO2) {
		this.donneePollutionSO2 = donneePollutionSO2;
		this.indicateurPollutionSO2 = determinerIndicateur("SO2");
	}

	public DonneePollution getDonneePollutionPM25() {
		return donneePollutionPM25;
	}

	public void setDonneePollutionPM25(DonneePollution donneePollutionPM25) {
		this.donneePollutionPM25 = donneePollutionPM25;
		this.indicateurPollutionPM25 = determinerIndicateur("PM2.5");
	}

	public DonneePollution getDonneePollutionO3() {
		return donneePollutionO3;
	}

	public void setDonneePollutionO3(DonneePollution donneePollutionO3) {
		this.donneePollutionO3 = donneePollutionO3;
		this.indicateurPollutionO3 = determinerIndicateur("O3");
	}

	public DonneePollution getDonneePollutionNO2() {
		return donneePollutionNO2;
	}

	public void setDonneePollutionNO2(DonneePollution donneePollutionNO2) {
		this.donneePollutionNO2 = donneePollutionNO2;
		this.indicateurPollutionNO2 = determinerIndicateur("NO2");
	}

	public DonneePollution getDonneePollutionCO() {
		return donneePollutionCO;
	}

	public void setDonneePollutionCO(DonneePollution donneePollutionCO) {
		this.donneePollutionCO = donneePollutionCO;
		this.indicateurPollutionCO = determinerIndicateur("CO");
	}

	
	private String determinerIndicateur(String string) {
		String[] indicateurs = {
				"#52E500",
				"#82E800",
				"#B4EB00",
				"#E8EE00",
				"#F2C600",
				"#F59700", 
				"#F86600",
				"#FB3300", 
				"#FF0000"
		};
		Double[] seuils = null;
		DonneePollution donneePollution = new DonneePollution();
		if (string.equals("PM10")) {
			donneePollution = this.donneePollutionPM10;
			seuils = this.seuilPM10;
		} else if (string.equals("PM2.5")) {
			donneePollution = this.donneePollutionPM25;
			seuils = this.seuilPM25;
		} else if (string.equals("SO2")) {
			donneePollution = this.donneePollutionSO2;
			seuils = this.seuilSO2;
		} else if (string.equals("NO2")) {
			donneePollution = this.donneePollutionNO2;
			seuils = this.seuilNO2;
		} else if (string.equals("CO")) {
			donneePollution = this.donneePollutionCO;
			seuils = this.seuilCO;
		} else if (string.equals("O3")) {
			donneePollution = this.donneePollutionO3;
			seuils = this.seuilO3;
		}
		
		int i = 0;
		while (i + 1 < seuils.length && donneePollution.getValeur() > seuils[i]) {
			i++;
		}
		return indicateurs[i];
	}

	public String getIndicateurPollutionPM10() {
		return indicateurPollutionPM10;
	}

	public String getIndicateurPollutionSO2() {
		return indicateurPollutionSO2;
	}

	public String getIndicateurPollutionPM25() {
		return indicateurPollutionPM25;
	}

	public String getIndicateurPollutionO3() {
		return indicateurPollutionO3;
	}

	public String getIndicateurPollutionNO2() {
		return indicateurPollutionNO2;
	}

	public String getIndicateurPollutionCO() {
		return indicateurPollutionCO;
	}

	public Zone(String code, String nom) {
		super();
		this.code = code;
		this.nom = nom;
	}

	
}

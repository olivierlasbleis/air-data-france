package com.lasbleiso.entities.vues;

public class SeuilsPollutionVue {
	
	private String seuilPollution;
	private String codeCouleur;
	
	
	public String getSeuilPollution() {
		return seuilPollution;
	}
	public void setSeuilPollution(String seuilPollution) {
		this.seuilPollution = seuilPollution;
	}
	public String getCodeCouleur() {
		return codeCouleur;
	}
	public void setCodeCouleur(String codeCouleur) {
		this.codeCouleur = codeCouleur;
	}
	public SeuilsPollutionVue(String seuilPollution, String codeCouleur) {
		super();
		this.seuilPollution = seuilPollution;
		this.codeCouleur = codeCouleur;
	}
	public SeuilsPollutionVue() {
		super();
	}

}

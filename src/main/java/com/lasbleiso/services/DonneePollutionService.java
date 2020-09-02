package com.lasbleiso.services;

import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.lasbleiso.entities.DonneePollution;
import com.lasbleiso.entities.StationPollution;
import com.lasbleiso.entities.vues.DepartementVue;
import com.lasbleiso.entities.vues.DonneePollutionVue;

@Service
public class DonneePollutionService {

	public DonneePollutionVue convertToDonneePollutionVue(DonneePollution donneePollution) {

		Double valeur = donneePollution.getValeur();

		 String uniteDeMesure = donneePollution.getUniteDeMesure();
		 String dateDeMesure = "";
		 String ageDeLaMesure = "";
		 if (donneePollution.getDateDeMesure() != null) {
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");
			 dateDeMesure = donneePollution.getDateDeMesure().format(formatter);

			 ZonedDateTime dateNow = ZonedDateTime.now();
			 Period p = Period.between(donneePollution.getDateDeMesure().toLocalDate(), dateNow.toLocalDate());
			 
			 if (p.getYears()>0) {
				 ageDeLaMesure = ageDeLaMesure + p.getYears() + " ans - ";
			}if (p.getMonths()>0) {
				 ageDeLaMesure = ageDeLaMesure + p.getMonths() + " mois - ";
			}if (p.getDays()>0) {
				 ageDeLaMesure = ageDeLaMesure + p.getDays() + " jours - ";
			}
		}
		 
		
		  
		return new DonneePollutionVue(valeur, uniteDeMesure, dateDeMesure,ageDeLaMesure);
		
	}
}

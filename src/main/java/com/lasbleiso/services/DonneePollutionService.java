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

		Double valeur = round(donneePollution.getValeur(),1);

		 String uniteDeMesure = donneePollution.getUniteDeMesure();
		 String dateDeMesure = "";
		 String ageDeLaMesure = "";
		 if (donneePollution.getDateDeMesure() != null) {
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
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
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}

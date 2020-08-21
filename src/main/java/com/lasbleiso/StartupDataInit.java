package com.lasbleiso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.lasbleiso.entities.MesurePollution;
import com.lasbleiso.entities.StationPollution;
import com.lasbleiso.entities.ValueCommunePollution;
import com.lasbleiso.services.ValueCommunePollutionService;
import com.lasbleiso.utils.InitUtils;


/**
 * Cette Classe initialise la bdd. Au demmarage de l'application, elle fait
 * appel à la methode init().
 * 
 */
@Component
public class StartupDataInit {


	@Value("${data.init}")
	private Boolean isDataInit;

	@Autowired
	ValueCommunePollutionService valueCommunePollutionService;
	
	@Autowired
	InitUtils initUtils;

	

	/**
	 * initialise la bdd Cette méthode init va être invoquée au démarrage de
	 * l'application. via la methode insererLaListeDesCommunes() issue de la
	 * classe InsertionEnBasDeDonneeService, elle fait des appels aux APIs et
	 * récupère la liste des stations metéo, des stations de pollutions, des
	 * communes des mesures de pollution et de météo puis elle les lie entre
	 * elles et les insère en BDD
	 */
	// La méthode init va être invoquée au démarrage de l'application.
	@EventListener(ContextRefreshedEvent.class)
	public void init() throws Exception {

		if(isDataInit) {
			List<MesurePollution> listeStationPollution = initUtils.insertionMesurePollution();
			initUtils.insertionCommunes();
			initUtils.insertionValueCommunePollutionPM10();
		}

	}

}

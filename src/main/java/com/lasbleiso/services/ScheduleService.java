package com.lasbleiso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lasbleiso.entities.MesurePollution;
import com.lasbleiso.utils.InitUtils;

@Service
@EnableScheduling
public class ScheduleService {
	@Autowired
	InitUtils initUtils;

	@Scheduled(fixedDelay = 36000000)
	/**
	 * 
	 * @throws Exception
	 */
	public void insererEnBaseToutesLes24h() throws Exception {
		
		List<MesurePollution> listeStationPollution = initUtils.insertionMesurePollution();
		initUtils.insertionCommunes();
		initUtils.moyennesDepartements();
	}
}

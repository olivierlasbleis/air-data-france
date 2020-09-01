package com.lasbleiso.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * controleur lié aux apikey afin qu'un utilisateur ne spam pas les requêtes
 * @author lasbl
 *
 */
@RestController
@RequestMapping("station-pollutions")
public class ApiKeyController {

	@GetMapping
	public String getApiKey() {
		return "";
	}
}

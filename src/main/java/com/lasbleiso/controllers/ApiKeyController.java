package com.lasbleiso.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("station-pollutions")
public class ApiKeyController {

	@GetMapping
	public String getApiKey() {
		return "";
	}
}

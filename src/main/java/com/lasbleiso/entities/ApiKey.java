package com.lasbleiso.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApiKey {

	@Id
	private String id;
	private LocalDateTime dateEnregistre;
	private Integer nbAppels;
}

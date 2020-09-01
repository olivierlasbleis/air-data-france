package com.lasbleiso.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Departement extends Zone{
	
	

	public Departement() {
		super();
	}

	public Departement(String codeDepartement, String nom) {
		super(codeDepartement,nom);
	}

	
}

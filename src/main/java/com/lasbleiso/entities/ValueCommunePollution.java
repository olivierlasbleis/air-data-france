package com.lasbleiso.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValueCommunePollution {

	/** id : Integer . est l'identifiant en base de donnée de la ValueCommunePollution */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	private int code;
	private Double rate;
	
	public ValueCommunePollution(int code, Double rate) {
		super();
		this.code = code;
		this.rate = rate;
	}

	
}

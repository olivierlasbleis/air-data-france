package com.lasbleiso.entities.geojson;

import java.util.List;

public class Geojson {
	
	private Feature[] features;
	private String type;
	public Feature[] getListeFeatures() {
		return features;
	}
	public void setListeFeatures(Feature[] features) {
		this.features = features;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

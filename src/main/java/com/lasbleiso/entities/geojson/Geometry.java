package com.lasbleiso.entities.geojson;

import java.util.List;

public class Geometry {
	private List<List<List<Double>>> coordinates;
	private String type;
	public List<List<List<Double>>> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<List<List<Double>>> coordinates) {
		this.coordinates = coordinates;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

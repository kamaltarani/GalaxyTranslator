package com.thoughtworks.assignment.business;

public interface BarsParser {

	public void addBarDetails(String line);
	
	public double getBarUnitValue(String barName);
	
	public boolean isValidBar(String name);
}

package com.thoughtworks.assignment.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.assignment.business.impl.BarsParserImpl;
import com.thoughtworks.assignment.util.AlienNumericConverterUtil;

public class BarsParserImplUnitTest {

	AlienNumericConverterUtil alienNumerics;
	BarsParser bar;

	@Before
	public void setUp() {
		alienNumerics = new AlienNumericConverterUtil();
		alienNumerics.addValueToAlienNumerals("glob", "I");
		alienNumerics.addValueToAlienNumerals("prok", "V");
		alienNumerics.addValueToAlienNumerals("pish", "X");
		alienNumerics.addValueToAlienNumerals("tegj", "L");
		bar = new BarsParserImpl(alienNumerics);
		bar.addBarDetails("glob glob Silver is 34 Credits");
		bar.addBarDetails("pish pish Iron is 3910 Credits");
		bar.addBarDetails("glob prok Gold is 57800 Credits");
	}

	@Test
	public void shouldCheckIfGivenBarIsPresent() {
		assertEquals(true, bar.isValidBar("Silver"));
		assertEquals(true, bar.isValidBar("Gold"));
		assertEquals(true, bar.isValidBar("Iron"));
		assertEquals(false, bar.isValidBar("Bronze"));
	}

	@Test
	public void shouldCheckBarUnitValue() {
		assertEquals("17.0", String.valueOf(bar.getBarUnitValue("Silver")));
		assertEquals("195.5", String.valueOf(bar.getBarUnitValue("Iron")));
		assertEquals("14450.0", String.valueOf(bar.getBarUnitValue("Gold")));
	}
}

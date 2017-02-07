package com.thoughtworks.assignment.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.assignment.util.AlienNumericConverterUtil;

public class AlientNumericConverterUtilUnitTest {
	AlienNumericConverterUtil alienNumerics;

	@Before
	public void setUp() {
		alienNumerics = new AlienNumericConverterUtil();
		alienNumerics.addValueToAlienNumerals("glob", "I");
		alienNumerics.addValueToAlienNumerals("prok", "V");
		alienNumerics.addValueToAlienNumerals("pish", "X");
		alienNumerics.addValueToAlienNumerals("tegj", "L");
	}

	@Test
	public void shouldValidateAlienNumerals() {
		assertEquals(true, alienNumerics.areValidAlienNumeral(new String[] { "glob", "prok" }));
		assertEquals(false, alienNumerics.areValidAlienNumeral(new String[] { "glob", "prok", "another" }));
	}

	@Test
	public void shouldValidateConversionToRomanNumerals() {
		assertEquals("IV", alienNumerics.toRomanNumeral(new String[] { "glob", "prok" }));
		assertEquals("VI", alienNumerics.toRomanNumeral(new String[] { "prok", "glob" }));
		assertEquals("XLII", alienNumerics.toRomanNumeral(new String[] { "pish", "tegj", "glob", "glob" }));
	}
}

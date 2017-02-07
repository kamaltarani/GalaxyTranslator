package com.thoughtworks.assignment.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RomanNumericHelperUtilUnitTest {
	
	@Test
	public void shouldValidateRomanToNumberConversion() {
		RomanNumericHelperUtil romanNumerics = new RomanNumericHelperUtil();
		assertEquals(3900, romanNumerics.convertRomanToNumber("MMMCM"));
		assertEquals(101, romanNumerics.convertRomanToNumber("CI"));
		assertEquals(105, romanNumerics.convertRomanToNumber("CV"));
		assertEquals(2, romanNumerics.convertRomanToNumber("II"));
		assertEquals(4, romanNumerics.convertRomanToNumber("IV"));
		assertEquals(1903, romanNumerics.convertRomanToNumber("MCMIII"));
		assertEquals(3900, romanNumerics.convertRomanToNumber("MMMCM"));
		assertEquals(9, romanNumerics.convertRomanToNumber("IX"));
		assertEquals(15, romanNumerics.convertRomanToNumber("XV"));
	}
}

package com.thoughtworks.assignment.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.assignment.enums.LineType;

public class RegexCalculatorUnitTest {

	RegexCalculator regexCalculator;

	@Before
	public void setUp() {
		regexCalculator = RegexCalculator.getInstance();
	}

	@Test
	public void shouldValidateLinetypes() {
		assertEquals(LineType.ASSIGNMENT, regexCalculator.getLineType("glob is I"));
		assertEquals(LineType.CREDITSINFO, regexCalculator.getLineType("glob glob Silver is 34 Credits"));
		assertEquals(LineType.QUESTION_HOW_MANY, regexCalculator.getLineType("how many Credits is glob prok Gold ?"));
		assertEquals(LineType.QUESTION_HOW_MUCH, regexCalculator.getLineType("how much is glob glob glob glob ?"));
		assertEquals(LineType.NONE, regexCalculator.getLineType("how much wood could a woodchuck chuck?"));
	}

	@Test
	public void shouldVoilateRomanRepitionValidity() {
		assertEquals(true, regexCalculator.isRomanNumeralsRepetitionValid("IIII"));
		assertEquals(true, regexCalculator.isRomanNumeralsRepetitionValid("XXXX"));
		assertEquals(true, regexCalculator.isRomanNumeralsRepetitionValid("CCCC"));
		assertEquals(true, regexCalculator.isRomanNumeralsRepetitionValid("MMMM"));
		assertEquals(true, regexCalculator.isRomanNumeralsRepetitionValid("DD"));
	}

	@Test
	public void shouldValidateWordRegularExp() {
		assertEquals(true, regexCalculator.isAlienNumeralWord("word"));
		assertEquals(false, regexCalculator.isAlienNumeralWord("123"));
		assertEquals(false, regexCalculator.isAlienNumeralWord("word123"));
	}
}

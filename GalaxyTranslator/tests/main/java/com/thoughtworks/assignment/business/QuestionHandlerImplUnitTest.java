package com.thoughtworks.assignment.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.assignment.business.impl.BarsParserImpl;
import com.thoughtworks.assignment.business.impl.QuestionHandlerImpl;
import com.thoughtworks.assignment.constants.RomanNumericConstants;
import com.thoughtworks.assignment.util.AlienNumericConverterUtil;

public class QuestionHandlerImplUnitTest {

	AlienNumericConverterUtil alienNumerics;
	BarsParser bar;
	QuestionHandler questionHandler;

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
		questionHandler = new QuestionHandlerImpl(alienNumerics, bar);
	}

	@Test
	public void shouldAnswerHowManyTypeQuestions() {
		assertEquals("glob prok Silver is 68.0 Credits",
				questionHandler.answerHowManyTypeQuestion("how many Credits is glob prok Silver ?"));
		assertEquals("glob prok Gold is 57800.0 Credits",
				questionHandler.answerHowManyTypeQuestion("how many Credits is glob prok Gold ?"));
		assertEquals("glob prok Iron is 782.0 Credits",
				questionHandler.answerHowManyTypeQuestion("how many Credits is glob prok Iron ?"));
		assertEquals(RomanNumericConstants.NO_IDEA,
				questionHandler.answerHowManyTypeQuestion("how many Credits is glob glob glob dummy value ?"));
	}

	@Test
	public void shouldAnswerHowMuchTypeQuestions() {
		assertEquals("pish tegj glob glob  is 42.",
				questionHandler.answerHowMuchTypeQuestion("how much is pish tegj glob glob ?"));
		assertEquals("glob glob glob glob  is 4.",
				questionHandler.answerHowMuchTypeQuestion("how much is glob glob glob glob ?"));
		assertEquals(RomanNumericConstants.NO_IDEA, questionHandler
				.answerHowMuchTypeQuestion("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"));
	}
}

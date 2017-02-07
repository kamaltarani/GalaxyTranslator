package com.thoughtworks.assignment.business.impl;

import java.util.regex.Matcher;

import com.thoughtworks.assignment.business.BarsParser;
import com.thoughtworks.assignment.business.QuestionHandler;
import com.thoughtworks.assignment.constants.RomanNumericConstants;
import com.thoughtworks.assignment.util.AlienNumericConverterUtil;
import com.thoughtworks.assignment.util.RegexCalculator;

public class QuestionHandlerImpl implements QuestionHandler{

	private RegexCalculator regexEngine = RegexCalculator.getInstance();
	private AlienNumericConverterUtil alienNumerics;
	private BarsParser bar;

	public QuestionHandlerImpl(AlienNumericConverterUtil numerics, BarsParser bar) {
		this.alienNumerics = numerics;
		this.bar = bar;
	}

	public String answerHowMuchTypeQuestion(String line) {
		String answer = null;
		Matcher howMuchMatcher = regexEngine.getHowMuchMatcher(line);

		if (howMuchMatcher.matches()) {
			String inputNumerals = howMuchMatcher.group(1);
			String[] alienNumerals = howMuchMatcher.group(1).split(" ");
			if (alienNumerics.areValidAlienNumeral(alienNumerals)) {
				int number = alienNumerics.convertRomanToNumber(alienNumerics.toRomanNumeral(alienNumerals));
				if (number != -1) {
					answer = inputNumerals + " is " + number + ".";
				}
			}
		}
		return answer != null ? answer : RomanNumericConstants.NO_IDEA;
	}

	public String answerHowManyTypeQuestion(String line) {
		String answer = null;
		Matcher howManyMatcher = regexEngine.getHowManyMatcher(line);

		if (howManyMatcher.matches()) {
			String creditName = howManyMatcher.group(1);
			String[] alienNumerals = howManyMatcher.group(2).split("\\s");
			String barName = howManyMatcher.group(3);

			if (alienNumerics.areValidAlienNumeral(alienNumerals) && bar.isValidBar(barName)) {
				int barQuantity = alienNumerics.convertRomanToNumber(alienNumerics.toRomanNumeral(alienNumerals));

				if (barQuantity != -1) {
					double totalCredits = barQuantity * bar.getBarUnitValue(barName);
					StringBuilder result = new StringBuilder();
					result.append(howManyMatcher.group(2) + barName + " is " + totalCredits + " " + creditName);
					answer = result.toString();
				}
			}
		}
		return answer != null ? answer : RomanNumericConstants.NO_IDEA;
	}
}

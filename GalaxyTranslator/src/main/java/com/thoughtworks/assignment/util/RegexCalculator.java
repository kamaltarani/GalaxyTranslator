package com.thoughtworks.assignment.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.assignment.constants.RomanNumericConstants;
import com.thoughtworks.assignment.enums.Line;
import com.thoughtworks.assignment.enums.LineType;

public class RegexCalculator {

	private static RegexCalculator regexCalc;

	private Matcher matcher;

	private Pattern pattern;

	private RegexCalculator() {
		if (RegexCalculator.regexCalc != null) {
			throw new InstantiationError("Creating new object is not allowed.");
		}
	}

	public static RegexCalculator getInstance() {
		if (regexCalc == null) {
			regexCalc = new RegexCalculator();
		}
		return regexCalc;
	}

	public LineType getLineType(String line) {
		for (Line l : Line.values()) {
			pattern = getPattern(l.getRegex());
			matcher = getMatcher(pattern, line);
			if (matcher.matches()) {
				return l.getType();
			}
		}
		return LineType.NONE;
	}

	public Pattern getPattern(String line) {
		return Pattern.compile(line);
	}

	public Matcher getMatcher(Pattern pattern, String line) {
		return pattern.matcher(line);
	}

	public boolean isRomanNumeralsRepetitionValid(String romanNumeral) {
		String regex = "";
		String[] repetitionTest = RomanNumericConstants.ROMAN_REPITITION_REG_EXPR;
		for (int i = 0; i < repetitionTest.length; i++) {
			regex = repetitionTest[i];
			Matcher repititionMatcher = getMatcher(getPattern(regex), romanNumeral);

			if (repititionMatcher.matches()) {
				DisplayResultsUtil.promptUser(
						"The value \"" + romanNumeral + "\" entered violates Roman Number repitition constraints.");
			}
		}
		return true;
	}

	public boolean isAlienNumeralWord(String numeral) {
		matcher = getMatcher(getPattern(RomanNumericConstants.WORD_REG_EXPR), (numeral.toLowerCase()));
		if (!matcher.matches()) {
			DisplayResultsUtil.promptUser("Numeral entered \"" + numeral + "\" must be a Word!");
		}
		return (matcher.matches() ? true : false);
	}

	public Matcher getHowMuchMatcher(String line) {
		return getMatcher(getPattern(RomanNumericConstants.HOW_MUCH_REG_EXPR), line);
	}

	public Matcher getHowManyMatcher(String line) {
		return getMatcher(getPattern(RomanNumericConstants.HOW_MANY_REG_EXPR), line);
	}

	public Matcher getCreditsMatcher(String line) {
		return getMatcher(getPattern(RomanNumericConstants.CREDIT_MATCHER_REG_EXPR), line);
	}
}

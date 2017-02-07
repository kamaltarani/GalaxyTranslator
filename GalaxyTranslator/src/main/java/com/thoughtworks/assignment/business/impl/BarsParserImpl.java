package com.thoughtworks.assignment.business.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import com.thoughtworks.assignment.business.BarsParser;
import com.thoughtworks.assignment.constants.RomanNumericConstants;
import com.thoughtworks.assignment.util.AlienNumericConverterUtil;
import com.thoughtworks.assignment.util.DisplayResultsUtil;
import com.thoughtworks.assignment.util.RegexCalculator;

public class BarsParserImpl implements BarsParser{

	private Map<String, Double> bars;
	private AlienNumericConverterUtil alienNumerics;
	private RegexCalculator regexCalculator = RegexCalculator.getInstance();

	public BarsParserImpl(AlienNumericConverterUtil numerics) {
		this.alienNumerics = numerics;
		bars = new HashMap<String, Double>();
	}

	public void addBarDetails(String line) {
		Matcher matcher = regexCalculator.getCreditsMatcher(line);

		if (matcher.matches()) {
			String[] alienNumerals = matcher.group(1).split("\\s");
			String name = matcher.group(2);
			int bulkValue = Integer.parseInt(matcher.group(3));

			if (!alienNumerics.areValidAlienNumeral(alienNumerals)) {
				DisplayResultsUtil.promptUser(RomanNumericConstants.UNDECLARED_ALIEN_NUMERAL);
				return;
			}
			float units = alienNumerics.convertRomanToNumber(alienNumerics.toRomanNumeral(alienNumerals));
			float barUnitValue = bulkValue / units;
			addbar(name, barUnitValue);
		}
	}

	public double getBarUnitValue(String barName) {
		return bars.get(barName);
	}

	private void addbar(String barName, double barUnitValue) {
		bars.put(barName, barUnitValue);
	}

	public boolean isValidBar(String name) {
		boolean result = bars.containsKey(name);

		if (!result) {
			DisplayResultsUtil.promptUser(name + " bar is not present.");
		}
		return result;
	}
}

package com.thoughtworks.assignment.util;

import java.util.HashMap;
import java.util.Map;

public class AlienNumericConverterUtil extends RomanNumericHelperUtil {

	private RegexCalculator regexCalculator = RegexCalculator.getInstance();

	private Map<String, String> alienNumerals;

	public AlienNumericConverterUtil() {
		alienNumerals = new HashMap<String, String>();
	}

	public void addValueToAlienNumerals(String alienNumeral, String romanNumeral) {
		if (regexCalculator.isAlienNumeralWord(alienNumeral) && !isValidAlienNumeral(alienNumeral)) {
			alienNumerals.put(alienNumeral, romanNumeral);
		}
	}

	public String toRomanNumeral(String[] alienNumeral) {
		StringBuilder romanNumbers = new StringBuilder(alienNumeral.length);
		for (String numeralValue : alienNumeral) {
			romanNumbers.append(alienNumerals.get(numeralValue));
		}
		return romanNumbers.toString();
	}

	private boolean isValidAlienNumeral(String alienNumeral) {
		return alienNumerals.containsKey(alienNumeral);
	}

	public boolean areValidAlienNumeral(String[] alienNumerals) {
		for (String numeral : alienNumerals) {
			if (!isValidAlienNumeral(numeral)) {
				DisplayResultsUtil.promptUser(numeral + " is not a Alien Numeral or has not been added.");
				return false;
			}
		}
		return true;
	}
	
	public void saveAssignment(String line) {
		String[] operands = getAssignmentOperands(line);
		addValueToAlienNumerals(operands[0], operands[1]);
	}

	private String[] getAssignmentOperands(String line) {
		String[] parts = line.split(" ");
		return new String[] { parts[0], parts[2] };
	}
}

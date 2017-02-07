package com.thoughtworks.assignment.util;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.assignment.constants.RomanNumericConstants;
import com.thoughtworks.assignment.enums.Numeral;

public class RomanNumericHelperUtil {

	private RegexCalculator regexCalculator = RegexCalculator.getInstance();

	private int getNumberValue(char numeral) {
		int temp = -1;
		switch (numeral) {
		case 'I':
			temp = Numeral.I.getNumber();
			break;
		case 'V':
			temp = Numeral.V.getNumber();
			break;
		case 'L':
			temp = Numeral.L.getNumber();
			break;
		case 'X':
			temp = Numeral.X.getNumber();
			break;
		case 'C':
			temp = Numeral.C.getNumber();
			break;
		case 'D':
			temp = Numeral.D.getNumber();
			break;
		case 'M':
			temp = Numeral.M.getNumber();
			break;
		default:
			DisplayResultsUtil.promptUser("Invalid Numeral Entry: " + numeral);
		}
		return temp;
	}

	private List<Integer> negateValueIfPreviousLessThanNext(List<Integer> numbers) {
		int currentElement;
		for (int i = 0; i < numbers.size() - 1; i++) {
			currentElement = numbers.get(i);
			if (currentElement < numbers.get(i + 1)) {
				numbers.set(i, -currentElement);
			}
		}
		return numbers;
	}

	public int convertRomanToNumber(String romanNumeral) {
		List<Integer> numbers = getNumbersFromRomanValue(romanNumeral.toCharArray());
		if (numbers != null) {
			numbers = negateValueIfPreviousLessThanNext(numbers);
			int resultInNumber = 0;
			if (regexCalculator.isRomanNumeralsRepetitionValid(romanNumeral)) {
				if (isRomanNumeralsSubtractionValid(numbers)) {
					for (int numberValue : numbers) {
						resultInNumber += numberValue;
					}
					return resultInNumber;
				}
			} else {
				DisplayResultsUtil.promptUser("Roman Numeral Subtraction condition not met.");
			}
		}
		return -1;
	}

	private List<Integer> getNumbersFromRomanValue(char[] romanNumerals) {
		List<Integer> numbers = new ArrayList<Integer>();
		for (char romanCharacter : romanNumerals) {
			try {
				numbers.add(getNumberValue(romanCharacter));
			} catch (IllegalArgumentException ex) {
				DisplayResultsUtil.promptUser("Incorrect Roman Numeral: " + romanCharacter);
				numbers = null;
			}
		}
		return numbers;
	}

	private boolean isRomanNumeralsSubtractionValid(List<Integer> numbers) {
		for (int i = 0; i < numbers.size() - 1; i++) {
			promptUserIfSubtractionNotPossibleForVLD(numbers.get(i));
			promtUserForCombinationOfSubtractionPossible(numbers.get(i), Math.abs(numbers.get(i + 1)));
		}
		return true;
	}

	private void promtUserForCombinationOfSubtractionPossible(int currentNumber, int nextNumber) {
		switch (Math.abs(currentNumber)) {
		case 1:
			if (nextNumber > Numeral.I.getNumber() && nextNumber != Numeral.V.getNumber()
					&& nextNumber != Numeral.X.getNumber() && nextNumber != currentNumber) {
				DisplayResultsUtil.promptUser(RomanNumericConstants.SUBTRACT_I_WITH_V_X);
			}
		case 10:
			if (nextNumber > Numeral.X.getNumber() && nextNumber != Numeral.L.getNumber()
					&& nextNumber != Numeral.C.getNumber() && nextNumber != currentNumber) {
				DisplayResultsUtil.promptUser(RomanNumericConstants.SUBTRACT_X_WITH_L_C);
			}
		case 100:
			if (nextNumber > Numeral.C.getNumber() && nextNumber != Numeral.D.getNumber()
					&& nextNumber != Numeral.M.getNumber() && nextNumber != currentNumber) {
				DisplayResultsUtil.promptUser(RomanNumericConstants.SUBTRACT_C_WITH_D_M);
			}
		}
	}

	private void promptUserIfSubtractionNotPossibleForVLD(int number) {
		if (number == -Numeral.V.getNumber() || number == -Numeral.L.getNumber()
				|| number == -Numeral.D.getNumber()) {
			DisplayResultsUtil.promptUser(RomanNumericConstants.SUBTRACT_NOT_POSSIBLE_V_L_D);
		}
	}
}

package com.thoughtworks.assignment.util;

import com.thoughtworks.assignment.constants.RomanNumericConstants;

public class DisplayResultsUtil {

	private static void printStatement(String text) {
		System.out.println("\n" + text + "\n");
	}

	public static void promptUser(String text) {
		printStatement(text);
	}

	public static void noIdea() {
		printStatement(RomanNumericConstants.NO_IDEA);
	}
}

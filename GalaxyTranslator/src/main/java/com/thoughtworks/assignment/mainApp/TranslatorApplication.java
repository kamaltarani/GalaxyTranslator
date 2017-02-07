package com.thoughtworks.assignment.mainApp;

import com.thoughtworks.assignment.business.NumeralParser;
import com.thoughtworks.assignment.business.impl.NumeralParserImpl;

public class TranslatorApplication {
	public static void main(String[] args) throws Exception {
		NumeralParser numeralParser = new NumeralParserImpl();
		numeralParser.parse();
	}
}

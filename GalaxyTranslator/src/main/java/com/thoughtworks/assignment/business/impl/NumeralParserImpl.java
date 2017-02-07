package com.thoughtworks.assignment.business.impl;

import java.util.Scanner;

import com.thoughtworks.assignment.business.BarsParser;
import com.thoughtworks.assignment.business.NumeralParser;
import com.thoughtworks.assignment.business.QuestionHandler;
import com.thoughtworks.assignment.enums.LineType;
import com.thoughtworks.assignment.util.AlienNumericConverterUtil;
import com.thoughtworks.assignment.util.DisplayResultsUtil;
import com.thoughtworks.assignment.util.RegexCalculator;

public class NumeralParserImpl implements NumeralParser {

	private Scanner inputScanner;
	private AlienNumericConverterUtil alienNumericConverter;
	private BarsParser barParser;
	private QuestionHandler questionHandler;
	private RegexCalculator regexCalculator = RegexCalculator.getInstance();

	public NumeralParserImpl() {
		inputScanner = new Scanner(System.in);
		alienNumericConverter = new AlienNumericConverterUtil();
		barParser = new BarsParserImpl(alienNumericConverter);
		questionHandler = new QuestionHandlerImpl(alienNumericConverter, barParser);
	}

	public void parse() throws Exception {
		while (inputScanner.hasNext()) {
			process(inputScanner.nextLine());
		}
		inputScanner.close();
	}

	public void process(String line) throws Exception {
		LineType lineType = regexCalculator.getLineType(line);

		switch (lineType) {
		case ASSIGNMENT:
			alienNumericConverter.saveAssignment(line);
			break;

		case CREDITSINFO:
			barParser.addBarDetails(line);
			break;

		case QUESTION_HOW_MANY:
			DisplayResultsUtil.promptUser(questionHandler.answerHowManyTypeQuestion(line));
			break;

		case QUESTION_HOW_MUCH:
			DisplayResultsUtil.promptUser(questionHandler.answerHowMuchTypeQuestion(line));
			break;

		case NONE:
			DisplayResultsUtil.noIdea();
			break;
		}
	}
}

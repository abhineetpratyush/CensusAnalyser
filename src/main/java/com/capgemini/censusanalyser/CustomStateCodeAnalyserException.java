package com.capgemini.censusanalyser;

enum ExceptionTypeStateCode{
	STATE_CODE_FILE_PROBLEM, STATE_CODE_PARSE_PROBLEM, STATE_CODE_HEADER_OR_DELIMITER_PROBLEM
}

@SuppressWarnings("serial")
public class CustomStateCodeAnalyserException extends Exception {
	public CustomStateCodeAnalyserException(ExceptionTypeStateCode exceptionType) {
		super(exceptionType.toString());
	}
}

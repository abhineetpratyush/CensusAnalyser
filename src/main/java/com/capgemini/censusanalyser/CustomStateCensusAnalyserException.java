package com.capgemini.censusanalyser;

enum ExceptionType{
	STATE_CENSUS_FILE_PROBLEM, STATE_CENSUS_PARSE_PROBLEM
}

@SuppressWarnings("serial")
public class CustomStateCensusAnalyserException extends Exception {
	public CustomStateCensusAnalyserException(ExceptionType exceptionType) {
		super(exceptionType.toString());
	}
}

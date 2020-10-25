package com.capgemini.censusanalyser;

enum ExceptionType{
	FILE_PROBLEM, PARSE_PROBLEM, HEADER_OR_DELIMITER_PROBLEM
}

@SuppressWarnings("serial")
public class CustomCSVBuilderException extends Exception{
	public CustomCSVBuilderException(ExceptionType exceptionType) {
		super(exceptionType.toString());
	}
}


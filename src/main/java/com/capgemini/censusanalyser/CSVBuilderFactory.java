package com.capgemini.censusanalyser;

public class CSVBuilderFactory {
	public static ICSVBuilder createCSVBuilder() {
		return new OpenCSVBuilder();
	}
}

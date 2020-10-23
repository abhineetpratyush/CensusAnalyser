package com.capgemini.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
	@CsvBindByName(column = "State", required = true)
	public String state;

	@CsvBindByName(column = "Code", required = true)
	public String code;
}


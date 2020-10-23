package com.capgemini.censusanalyser;

import org.junit.Assert;
import org.junit.Test;

public class StateCodeTest {
	private final String STATE_CODE_CSV_FILE = "./src/main/resources/StateCodeCSVData.csv";
	private final String INCORRECT_STATE_CENSUS_CSV_FILE = "./src/main/resources/_StateCodeCSVData.csv";
	
	@Test
	public void givenTheStateCodeCSVFile_WhenRead_NoOfRecordsShouldMatch() throws CustomStateCodeAnalyserException {
		StateCensusAnalyser stateCodeAnalyser = new StateCensusAnalyser();
		int numOfRecords = stateCodeAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE);
		Assert.assertEquals(5, numOfRecords);
	}
	
	@Test
	public void givenIncorrectCSVFile_ShouldReturnCustomException() {
		String exceptionMessage = null;
		try {
			StateCensusAnalyser stateCodeAnalyser = new StateCensusAnalyser();
			stateCodeAnalyser.loadStateCodeData(INCORRECT_STATE_CENSUS_CSV_FILE);
		} catch(CustomStateCodeAnalyserException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionTypeStateCode.STATE_CODE_FILE_PROBLEM.toString(), exceptionMessage);
	}
}	
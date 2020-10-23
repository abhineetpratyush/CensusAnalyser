package com.capgemini.censusanalyser;

import org.junit.Assert;
import org.junit.Test;

public class StateCodeTest {
	private final String STATE_CODE_CSV_FILE = "./src/main/resources/StateCodeCSVData.csv";
	
	@Test
	public void givenTheStateCodeCSVFile_WhenRead_NoOfRecordsShouldMatch() {
		StateCensusAnalyser stateCodeAnalyser = new StateCensusAnalyser();
		int numOfRecords = stateCodeAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE);
		Assert.assertEquals(5, numOfRecords);
	}
}	
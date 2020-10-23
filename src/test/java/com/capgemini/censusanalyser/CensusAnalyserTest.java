package com.capgemini.censusanalyser;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest {
	private final String STATE_CENSUS_CSV_FILE = "./src/main/resources/StateCensusCSVData.csv";

	@Test
	public void givenTheStatesCensusCSVFile_WhenRead_NoOfRecordsShouldMatch() throws IOException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int numOfRecords = stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_CSV_FILE);
		Assert.assertEquals(7, numOfRecords);
	}
}

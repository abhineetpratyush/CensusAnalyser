package com.capgemini.censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;

public class CensusAnalyserTest {
	private final String STATE_CENSUS_CSV_FILE = "./src/main/resources/StateCensusCSVData.csv";
	private final String INCORRECT_STATE_CENSUS_CSV_FILE = "./src/main/resources/_StateCensusCSVData.csv";
	private final String INCORRECT_HEADER_STATE_CENSUS_CSV_FILE = "./src/main/resources/StateCensusCSVDataIncorrectHeader.csv";

	@Test
	public void givenTheStatesCensusCSVFile_WhenRead_NoOfRecordsShouldMatch() throws CustomFileIOException, CustomCSVBuilderException  {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
		mappingStrategy.setType(CSVStateCensus.class);
		int numOfRecords = stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, ',');
		Assert.assertEquals(6, numOfRecords);
	}

	@Test
	public void givenIncorrectCSVFile_ShouldReturnCustomException() throws CustomCSVBuilderException {
		String exceptionMessage = null;
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
			mappingStrategy.setType(CSVStateCensus.class);
			stateCensusAnalyser.loadStateCensusData(INCORRECT_STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, ',');
		} catch(CustomFileIOException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionTypeIO.FILE_PROBLEM.toString(), exceptionMessage);
	}
	
	@Test
	public void givenIncorrectCSVType_ShouldReturnCustomException() throws CustomFileIOException {
		String exceptionMessage = null;
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_CSV_FILE, null, null, ',');
		} catch(CustomCSVBuilderException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionType.PARSE_PROBLEM.toString(), exceptionMessage);
	}
	
	@Test
	public void givenCorrectCSVFileIncorrectDelimiter_ShouldReturnCustomException() throws CustomFileIOException {
		String exceptionMessage = null;
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
			mappingStrategy.setType(CSVStateCensus.class);
			stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, '|');
		} catch(CustomCSVBuilderException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionType.HEADER_OR_DELIMITER_PROBLEM.toString(), exceptionMessage);
	}
	
	@Test
	public void givenCorrectCSVFileIncorrectHeader_ShouldReturnCustomException() throws CustomFileIOException {
		String exceptionMessage = null;
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			MappingStrategy<CSVStateCensus> mappingStrategy = new HeaderColumnNameMappingStrategy<CSVStateCensus>();
			mappingStrategy.setType(CSVStateCensus.class);
			stateCensusAnalyser.loadStateCensusData(INCORRECT_HEADER_STATE_CENSUS_CSV_FILE, mappingStrategy, CSVStateCensus.class, ',');
		} catch(CustomCSVBuilderException e) {
			exceptionMessage = e.getMessage();
		}
		Assert.assertEquals(ExceptionType.HEADER_OR_DELIMITER_PROBLEM.toString(), exceptionMessage);
	}
}

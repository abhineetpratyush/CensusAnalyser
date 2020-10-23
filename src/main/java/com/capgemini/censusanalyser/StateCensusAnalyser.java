package com.capgemini.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	public int loadStateCensusData(String csvFilePath) throws CustomStateCensusAnalyserException {
		try {	
		Reader reader;
			reader = Files.newBufferedReader(Paths.get(csvFilePath));
			CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(CSVStateCensus.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVStateCensus> csvStateCensusIterator = csvToBean.iterator();
			Iterable<CSVStateCensus> csvStateCensusIterable = () -> csvStateCensusIterator;
			int numOfEntries = (int) StreamSupport.stream(csvStateCensusIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (IOException e) {
			throw new CustomStateCensusAnalyserException(ExceptionType.STATE_CENSUS_FILE_PROBLEM);
		}
		catch(IllegalStateException e) {
			throw new CustomStateCensusAnalyserException(ExceptionType.STATE_CENSUS_PARSE_PROBLEM);
		}
	}

	public int loadStateCodeData(String csvFilePath) throws IOException{
		Reader reader;
		reader = Files.newBufferedReader(Paths.get(csvFilePath));
		CsvToBeanBuilder<CSVStates> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
		csvToBeanBuilder.withType(CSVStates.class);
		csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
		CsvToBean<CSVStates> csvToBean = csvToBeanBuilder.build();
		Iterator<CSVStates> csvStateCodeIterator = csvToBean.iterator();
		Iterable<CSVStates> csvStateCodeIterable = () -> csvStateCodeIterator;
		int numOfEntries = (int) StreamSupport.stream(csvStateCodeIterable.spliterator(), false).count();
		return numOfEntries;
	}
}

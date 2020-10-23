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
	public int loadStateCensusData(String csvfilePath) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(csvfilePath));
		CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
		csvToBeanBuilder.withType(CSVStateCensus.class);
		csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
		CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
		Iterator<CSVStateCensus> csvStateCensusIterator = csvToBean.iterator();
		Iterable<CSVStateCensus> csvStateCensusIterable = () -> csvStateCensusIterator;
		int numOfEntries = (int) StreamSupport.stream(csvStateCensusIterable.spliterator(), false).count();
		return numOfEntries;
	}
}


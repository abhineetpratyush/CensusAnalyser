package com.capgemini.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;
import com.opencsv.bean.MappingStrategy;

public class StateCensusAnalyser {
	public int loadStateCensusData(String csvFilePath, MappingStrategy<CSVStateCensus> mappingStrategy, Class<? extends CSVStateCensus> csvBinderClass, final char separator) throws CustomStateCensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){	
			Iterator<CSVStateCensus> csvStateCensusIterator;
			if(csvBinderClass != null)
				csvStateCensusIterator = new OpenCSVBuilder().getCSVFileIterator(reader, CSVStateCensus.class, mappingStrategy, separator);
			else
				csvStateCensusIterator = new OpenCSVBuilder().getCSVFileIterator(reader, null, null, separator);
			return getCount(csvStateCensusIterator);
		} catch (IOException e) {
			throw new CustomStateCensusAnalyserException(ExceptionType.STATE_CENSUS_FILE_PROBLEM);
		} catch(IllegalStateException e) {
			throw new CustomStateCensusAnalyserException(ExceptionType.STATE_CENSUS_PARSE_PROBLEM);
		} catch(RuntimeException e) {
			throw new CustomStateCensusAnalyserException(ExceptionType.STATE_CENSUS_HEADER_OR_DELIMITER_PROBLEM);
		}
	}

	public int loadStateCodeData(String csvFilePath, MappingStrategy<CSVStates> mappingStrategy, Class<? extends CSVStates> csvBinderClass, final char separator) throws CustomStateCodeAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){	
			Iterator<CSVStates> csvStateCodeIterator;
			if(csvBinderClass != null)
				csvStateCodeIterator = new OpenCSVBuilder().getCSVFileIterator(reader, CSVStates.class, mappingStrategy, separator);
			else
				csvStateCodeIterator = new OpenCSVBuilder().getCSVFileIterator(reader, null, null, separator);
			return getCount(csvStateCodeIterator);
		} catch (IOException e) {
			throw new CustomStateCodeAnalyserException(ExceptionTypeStateCode.STATE_CODE_FILE_PROBLEM);
		} catch(IllegalStateException e) {
			throw new CustomStateCodeAnalyserException(ExceptionTypeStateCode.STATE_CODE_PARSE_PROBLEM);
		} catch(RuntimeException e) {
			throw new CustomStateCodeAnalyserException(ExceptionTypeStateCode.STATE_CODE_HEADER_OR_DELIMITER_PROBLEM);
		}
	}
	
	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numOfEntries;
	}
}

package com.capgemini.censusanalyser;

import java.io.Reader;
import java.util.Iterator;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.MappingStrategy;

public class OpenCSVBuilder<E> implements ICSVBuilder {
	public Iterator<E> getCSVFileIterator(Reader reader, Class csvBinderClass,
			MappingStrategy mappingStrategy, final char separator) throws CustomCSVBuilderException {
		try {
		CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
		csvToBeanBuilder.withMappingStrategy(mappingStrategy);
		csvToBeanBuilder.withType(csvBinderClass);
		csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
		csvToBeanBuilder.withSeparator(separator);
		CsvToBean<E> csvToBean = csvToBeanBuilder.build();
		return csvToBean.iterator();
	}catch(IllegalStateException e) {
		throw new CustomCSVBuilderException(ExceptionType.PARSE_PROBLEM);
	} catch(RuntimeException e) {
		throw new CustomCSVBuilderException(ExceptionType.HEADER_OR_DELIMITER_PROBLEM);
	}
}
}

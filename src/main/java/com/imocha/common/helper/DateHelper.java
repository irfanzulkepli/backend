package com.imocha.common.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DateHelper {

	private final String DATE_FORMAT = "yyyy-MM-dd";

	public Date getDateFromString(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Date dateFrom = null;
		try {
			dateFrom = sdf.parse(dateString);
		} catch (ParseException e) {
			log.debug("Date Parse Exception");
			log.debug(e.getMessage());
		}
		return dateFrom;
	}
}

package com.mcb.mcsharesproject.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This utility class is to perform LocalDate operations.
 */
public class LocalDateUtils {

    private static final DateTimeFormatter XML_FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDateUtils() {
    }

    /**
     * @param xmlStringDate
     * @return
     *
     * This methods takes a non-null string date into the format "dd/MM/yyyy" and
     * converts it into LocalDate object.
     */
    public static LocalDate convertToLocalDate(String xmlStringDate) {
        return LocalDate.parse(xmlStringDate, XML_FILE_DATE_FORMAT);
    }
}

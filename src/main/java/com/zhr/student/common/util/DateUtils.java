package com.zhr.student.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 关于日期的工具类
 *
 * @author Harry
 */
public class DateUtils {

    public static final String FORMAT_V1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_V2 = "yyyy-MM-dd";
    public static final String FORMAT_V3 = "yyyy-MM";
    public static final String FORMAT_V4 = "yyyy";

    public static String formatDate(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(date);
    }

    public static LocalDate parseDate(String dateStr, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateStr, formatter);
    }


    public static Integer getAge(LocalDate birthDay) {
        LocalDate localDate = LocalDate.from(birthDay);
        return (int) ChronoUnit.YEARS.between(localDate, LocalDate.now());
    }

}

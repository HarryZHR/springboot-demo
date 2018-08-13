package com.zhr.student.common.util;

import java.time.LocalDateTime;
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

    public static String formatDate(LocalDateTime date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(date);
    }

    public static LocalDateTime parseDate(String dateStr, String format) {
        if (format == null) {
            format = FORMAT_V1;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateStr, formatter);
    }


    public static Integer getAge(LocalDateTime birthDay) {
        LocalDateTime localDateTime = LocalDateTime.from(birthDay);
        return (int) ChronoUnit.YEARS.between(localDateTime, LocalDateTime.now());
    }

}

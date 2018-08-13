package com.zhr.student.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 关于日期的工具类
 *
 * @author Harry
 */
public class DateUtils {

    /*public static final String FORMAT_V1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_V2 = "yyyy-MM-dd";
    public static final String FORMAT_V3 = "yyyy-MM";
    public static final String FORMAT_V4 = "yyyy";

    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date parseDate(String dateStr, String format) {
        if (format == null) {
            format = FORMAT_V1;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr, new ParsePosition(0));
    }*/


    public static Integer getAge(Date birthDay) {
        LocalDateTime time = LocalDateTime.ofInstant(birthDay.toInstant(), ZoneId.systemDefault());
        return time.toLocalDate().until(LocalDate.now()).getYears();
    }

}

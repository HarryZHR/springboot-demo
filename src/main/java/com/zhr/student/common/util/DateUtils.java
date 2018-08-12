package com.zhr.student.common.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final String FORMAT_V1 = "yyyy-MM-dd HH:mm:ss";
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
    }


    public static Integer getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            } else {
                age--;
            }
        }
        return age;
    }
}

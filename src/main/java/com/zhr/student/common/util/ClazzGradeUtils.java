package com.zhr.student.common.util;

import com.zhr.student.entity.Clazz;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * 获取年级中文的工具类
 *
 * @author Harry
 */
public class ClazzGradeUtils {

    public static String getGrade(Integer grade) {
        String gradeNum;
        switch (grade) {
            case 1:
                gradeNum = "一";
                break;
            case 2:
                gradeNum = "二";
                break;
            case 3:
                gradeNum = "三";
                break;
            case 4:
                gradeNum = "四";
                break;
            case 5:
                gradeNum = "五";
                break;
            case 6:
                gradeNum = "六";
                break;
            default:
                return "未知";
        }
        return gradeNum;
    }

    public static String getClazzName(Clazz clazz) {
        return clazz.getType().getValue().substring(0, 1) +
                getGrade(clazz.getGrade()).substring(0, 1) +
                "(" + clazz.getClazzNum() + ")";
    }

    public static String getGradeName(Clazz clazz) {
        return clazz.getType().getValue().substring(0, 1) +
                getGrade(clazz.getGrade()).substring(0, 1);
    }

    public static Integer getGradeByYear(Integer year) {
        LocalDate starDate = LocalDate.of(year, 6, 30);
        Period period = Period.between(starDate, LocalDate.now());
        if (period.getYears() == 0 && period.getMonths() >= 0 && period.getMonths() <= 12) {
            return 1;
        } else if (period.getYears() == 1 && period.getMonths() >= 0 && period.getMonths() <= 12) {
            return 2;
        } else if (period.getYears() == 2 && period.getMonths() >= 0 && period.getMonths() <= 12) {
            return 3;
        }
        return null;
    }

}

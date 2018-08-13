package com.zhr.student.dto.clazz;

import java.util.List;

/**
 * 班级的年级和班级号的信息
 *
 * @author Harry
 */
public class ClazzInfoDTO {
    private List<Integer> clazzNums;
    private List<Integer> grades;

    public List<Integer> getClazzNums() {
        return clazzNums;
    }

    public void setClazzNums(List<Integer> clazzNums) {
        this.clazzNums = clazzNums;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "ClazzInfoDTO{" +
                "clazzNums=" + clazzNums +
                ", grades=" + grades +
                '}';
    }
}

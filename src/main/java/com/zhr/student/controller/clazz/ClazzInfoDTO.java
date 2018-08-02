package com.zhr.student.controller.clazz;

import java.util.List;

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
}

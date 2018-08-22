package com.zhr.student.entity;

import lombok.Data;

/**
 * 班级和学生关系表
 *
 * @author Harry
 */
@Data
public class ClazzStudent {
    /**
     * id
     */
    private Long id;

    /**
     * 学生
     */
    private Student student;

    /**
     * 学生在班级里的学号
     */
    private Integer studentClazzNo;

    /**
     * 学生所在班级号
     */
    private Integer clazzNum;
}

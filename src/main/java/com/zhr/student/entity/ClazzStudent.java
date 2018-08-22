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
     * 班级
     */
    private Clazz clazz;
    /**
     * 学生
     */
    private Student student;
    /**
     * 学期 1、2、3、4、5、6
     */
    private Integer semester;

}

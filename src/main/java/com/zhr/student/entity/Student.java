package com.zhr.student.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 学生对象
 *
 * @author Harry
 */
@Data
public class Student {

    /**
     * 学生的id
     */
    private Long studentId;

    /**
     * 学生的学号
     */
    private String studentNum;

    /**
     * 学生的姓名
     */
    private String studentName;

    /**
     * 学生的性别
     */
    private String gender;

    /**
     * 学生的生日
     */
    private LocalDateTime birthday;

    /**
     * 学生的班级
     */
    private Clazz clazz;

    /**
     * 学生账号的登陆密码
     */
    private String password;

    /**
     * 是否删除
     */
    private Boolean deleteFlag;


}

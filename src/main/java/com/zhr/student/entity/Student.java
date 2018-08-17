package com.zhr.student.entity;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 学生对象
 *
 * @author Harry
 */
@Data
public class Student {

    @Getter
    public enum StudentGender{
        /**
         * 性别为男
         */
        male("男"),

        female("女");

        private String value;

        StudentGender(String value) {
            this.value = value;
        }
    }
    /**
     * 学生的id
     */
    private Long studentId;

    /**
     * 学生的姓名
     */
    private String studentName;

    /**
     * 学生的性别
     */
    private StudentGender gender;

    /**
     * 学生的生日
     */
    private LocalDate birthday;

    /**
     * 学生账号的登陆密码
     */
    private String password;

    /**
     * 是否删除
     */
    private Boolean deleteFlag;

    /**
     * 所属的学校
     */
    private School school;

}

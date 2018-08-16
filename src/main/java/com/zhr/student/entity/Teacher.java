package com.zhr.student.entity;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 教师对象
 *
 * @author Harry
 */
@Data
public class Teacher {

    public enum TeacherIdentity {
        /**
         * 老师是管理员的身份
         */
        admin("管理员"),

        /**
         * 老师是教师的身份
         */
        teacher("教师");

        @Getter
        private String value;

        TeacherIdentity(String value) {
            this.value = value;
        }

    }

    public enum TeacherGender {
        /**
         * 性别为男
         */
        male("男"),
        /**
         * 性别为女
         */
        female("女");

        TeacherGender(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 教师的id
     */
    private Long teacherId;

    /**
     * 教师的工号
     */
    private String teacherNum;

    /**
     * 教师的姓名
     */
    private String teacherName;

    /**
     * 教师的性别
     */
    private TeacherGender gender;

    /**
     * 教师的生日
     */
    private LocalDate birthday;

    /**
     * 教师的身份 管理员还是教师
     */
    private TeacherIdentity identity;

    /**
     * 教师的登陆密码
     */
    private String password;

    /**
     * 所属的学校
     */
    private School school;

    /**
     * 是否被删除
     */
    private Boolean deleteFlag;

    /**
     * 创建时间
     */
    private LocalDate createTime;
}

package com.zhr.student.entity;

import java.time.LocalDateTime;

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

        private String value;

        TeacherIdentity(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
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
    private LocalDateTime birthday;

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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(String teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public TeacherGender getGender() {
        return gender;
    }

    public void setGender(TeacherGender gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }


    public TeacherIdentity getIdentity() {
        return identity;
    }

    public void setIdentity(TeacherIdentity identity) {
        this.identity = identity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

}

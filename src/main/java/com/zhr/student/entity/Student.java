package com.zhr.student.entity;

import java.time.LocalDateTime;

/**
 * 学生对象
 *
 * @author Harry
 */
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

    private Boolean deleteFlag;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}

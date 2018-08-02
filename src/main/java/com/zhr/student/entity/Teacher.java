package com.zhr.student.entity;

import com.zhr.student.common.BaseEntity;
import com.zhr.student.common.SnowflakeIdWorker;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

public class Teacher extends BaseEntity {

    private String teacherId;

    private String teacherNum;

    private String teacherName;

    private String gender;

    private Date birthday;

    private List<Course> courses;

    private String identity;

    private String password;

    private School school;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
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

    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isNotBlank(teacherId)) {
            teacherId =  String.valueOf(snowflakeIdWorker.nextId());
        }
    }
}

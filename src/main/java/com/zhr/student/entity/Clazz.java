package com.zhr.student.entity;

import java.util.List;

/**
 * 班级对象
 *
 * @author Harry
 */
public class Clazz {

    /**
     * 班级的id
     */
    private Long clazzId;

    /**
     * 班级所在年级
     */
    private Integer grade;

    /**
     * 班级号
     */
    private Integer clazzNum;

    /**
     * 班主任
     */
    private Teacher headTeacher;

    /**
     * 是否删除
     */
    private Boolean deleteFlag;

    /**
     * 所属学校
     */
    private School school;

    /**
     * 学校里的学生
     */
    private List<Student> students;

    public Long getClazzId() {
        return clazzId;
    }

    public void setClazzId(Long clazzId) {
        this.clazzId = clazzId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getClazzNum() {
        return clazzNum;
    }

    public void setClazzNum(Integer clazzNum) {
        this.clazzNum = clazzNum;
    }

    public Teacher getHeadTeacher() {
        return headTeacher;
    }

    public void setHeadTeacher(Teacher headTeacher) {
        this.headTeacher = headTeacher;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

}

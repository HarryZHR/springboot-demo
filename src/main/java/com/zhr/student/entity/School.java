package com.zhr.student.entity;

/**
 * 学校的对象
 *
 * @author Harry
 */
public class School {
    /**
     * 学校的id
     */
    private Long schoolId;

    /**
     * 学校的名称
     */
    private String schoolName;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

}

package com.zhr.student.entity;

public class Score {

    private Long scoreId;

    private Student student;

    private Course course;

    private Float scoreNum;

    public Long getScoreId() {
        return scoreId;
    }

    public void setScoreId(Long scoreId) {
        this.scoreId = scoreId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Float getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Float scoreNum) {
        this.scoreNum = scoreNum;
    }

}

package com.zhr.student.entity;

import com.zhr.student.common.BaseEntity;
import com.zhr.student.common.util.SnowflakeIdWorker;
import org.apache.commons.lang3.StringUtils;

public class Score extends BaseEntity {

    private String scoreId;

    private Student student;

    private Course course;

    private Float scoreNum;

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
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

    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isNotBlank(scoreId)) {
            scoreId =  String.valueOf(snowflakeIdWorker.nextId());
        }
    }
}

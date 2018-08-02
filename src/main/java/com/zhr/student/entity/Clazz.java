package com.zhr.student.entity;

import com.zhr.student.common.BaseEntity;
import com.zhr.student.common.SnowflakeIdWorker;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Clazz extends BaseEntity {

    /**
     * 文理科枚举类
     */
    public enum ClazzType {
        /**
         * 文科
         */
        Liberal_arts("文科"),
        /**
         * 理科
         */
        science("理科");

        private String value;

        ClazzType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private String clazzId;

    private Integer grade;

    private Integer clazzNum;

    private Teacher headTeacher;

    private ClazzType type;

    private Boolean deleteFlag;

    private School school;

    private List<Student> students;

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
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

    public ClazzType getType() {
        return type;
    }

    public void setType(ClazzType type) {
        this.type = type;
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

    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isBlank(clazzId)) {
            clazzId = String.valueOf(snowflakeIdWorker.nextId());
        }
    }
}

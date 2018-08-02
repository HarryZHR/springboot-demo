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

    /**
     * 班级的id
     */
    private String clazzId;

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
     * 文理科类型
     */
    private ClazzType type;

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

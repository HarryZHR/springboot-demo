package com.zhr.student.entity;

import com.zhr.student.common.BaseEntity;
import com.zhr.student.common.SnowflakeIdWorker;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Clazz extends BaseEntity {

    private String clazzId;

    private String clazzName;

    private List<Student> students;

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isBlank(clazzId)) {
            clazzId = String.valueOf(snowflakeIdWorker.nextId());
        }
    }
}

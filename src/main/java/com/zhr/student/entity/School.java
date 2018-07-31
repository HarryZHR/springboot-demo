package com.zhr.student.entity;

import com.zhr.student.common.BaseEntity;
import com.zhr.student.common.SnowflakeIdWorker;
import org.apache.commons.lang3.StringUtils;

public class School extends BaseEntity {
    private String schoolId;

    private String schoolName;

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isNotBlank(schoolId)) {
            schoolId =  String.valueOf(snowflakeIdWorker.nextId());
        }
    }
}

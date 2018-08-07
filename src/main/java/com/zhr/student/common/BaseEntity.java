package com.zhr.student.common;

import com.zhr.student.common.util.SnowflakeIdWorker;
import com.zhr.student.common.util.SpringContextUtils;

import java.beans.Transient;

public abstract class BaseEntity{


    @Transient
    public abstract void setEntityId(SnowflakeIdWorker snowflakeIdWorker);
}
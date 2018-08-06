package com.zhr.student.common;

import com.zhr.student.common.util.SnowflakeIdWorker;
import com.zhr.student.common.util.SpringContextUtils;

import java.beans.Transient;

public abstract class BaseEntity{

    public BaseEntity(){
        SnowflakeIdWorker snowflakeIdWorker = SpringContextUtils.getBean("snowflakeIdWorker");
        setEntityId(snowflakeIdWorker);
    }

    @Transient
    public abstract void setEntityId(SnowflakeIdWorker snowflakeIdWorker);
}
package com.zhr.student.common;

import java.beans.Transient;

public abstract class BaseEntity{

    @Transient
    public abstract void setEntityId(SnowflakeIdWorker snowflakeIdWorker);
}
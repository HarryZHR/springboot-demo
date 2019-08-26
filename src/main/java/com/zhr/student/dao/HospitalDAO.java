package com.zhr.student.dao;

import com.zhr.student.entity.Hospital;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HospitalDAO {

    @Select("SELECT * FROM hospital WHERE name = #{name}")
    Hospital findOne(@Param(value = "name") String name);
}

package com.zhr.student.dao;

import com.zhr.student.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StoreDAO {
    @Select("SELECT * FROM store WHERE name = #{name}")
    Store findByName(@Param(value = "name") String name);
}

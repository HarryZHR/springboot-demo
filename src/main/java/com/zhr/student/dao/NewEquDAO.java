package com.zhr.student.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NewEquDAO {

    @Insert("INSERT INTO `new_equ` ( equ_id, category_id) " +
            "VALUES (#{equId}, #{categoryId} ")
    Integer saveEqu(@Param(value = "equId") String equId, @Param(value = "categoryId") String categoryId);
}

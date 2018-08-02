package com.zhr.student.repository;

import com.zhr.student.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SchoolRepository {
    /**
     * 根据id查询学校
     * @param schoolId 学校id
     * @return 学校对象
     */
    @Select("SELECT * FROM `school` WHERE school_id = #{schoolId}")
    School getSchoolById(@Param(value = "schoolId") String schoolId);
}

package com.zhr.student.repository;

import com.zhr.student.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherRepository {

    @Select("SELECT * FROM teacher WHERE teacher_id = #{teacherId} AND password = #{password}")
    Teacher getTeacherByIdAndPassword(@Param(value = "teacherId") String TeacherId, @Param(value = "password") String password);
}

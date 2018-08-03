package com.zhr.student.repository;

import com.zhr.student.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherRepository {

    @Select("SELECT * FROM `teacher` WHERE teacher_num = #{teacherNum} AND password = #{password}")
    Teacher getTeacherByNumAndPassword(@Param(value = "teacherNum") String TeacherNum, @Param(value = "password") String password);

    @Select("SELECT * FROM `teacher` WHERE teacher_id = #{teacherId}")
    Teacher getTeacherById(@Param(value = "teacherId") String teacherId);
}

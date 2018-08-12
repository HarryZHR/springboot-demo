package com.zhr.student.dao;

import com.github.pagehelper.Page;
import com.zhr.student.entity.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherDAO {

    @Select("SELECT * FROM `teacher` WHERE teacher_num = #{teacherNum} AND password = #{password}")
    Teacher getTeacherByNumAndPassword(@Param(value = "teacherNum") String TeacherNum, @Param(value = "password") String password);

    @Select("SELECT * FROM `teacher` WHERE teacher_id = #{teacherId}")
    Teacher getTeacherById(@Param(value = "teacherId") Long teacherId);

    @Select("SELECT * FROM `teacher` WHERE school_id= #{schoolId} AND identity = 'teacher'")
    List<Teacher> listTeacher(@Param(value = "schoolId") Long schoolId);

    @Select("<script>SELECT * FROM `teacher` WHERE identity = 'teacher'" +
            "<if test='teacherNum != null'> AND teacher_num = #{teacherNum} </if>" +
            "<if test='teacherName != null'> AND teacher_name LIKE #{teacherName} </if></script>")
    Page<Teacher> listTeacherByPage(@Param(value = "teacherNum") String teacherNum,
                                    @Param(value = "teacherName") String teacherName);

    @Insert("INSERT INTO `teacher` VALUES (#{teacherId},#{teacherNum}, #{teacherName}, #{birthday}, #{gender}, #{identity}, #{password}, #{school.schoolId})")
    Integer saveTeacher(Teacher teacher);
}
package com.zhr.student.repository;

import com.zhr.student.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM student")
    List<Student> listStudent();

    @Select("SELECT * FROM student where student_id=#{studentId} and password = #{password}")
    Student getStudentByIdAndPassword(@Param(value = "studentId") String studentId,@Param(value = "password") String password);
}

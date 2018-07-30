package com.zhr.student.repository;

import com.zhr.student.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM student")
    List<Student> listStudent();
}

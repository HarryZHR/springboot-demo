package com.zhr.student.controller;

import com.zhr.student.entity.Student;
import com.zhr.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public List<Student> listStudent(){
        return studentService.listStudent();
    }
}

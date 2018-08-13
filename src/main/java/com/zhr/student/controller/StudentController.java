package com.zhr.student.controller;

import com.zhr.student.common.result.Result;
import com.zhr.student.entity.Student;
import com.zhr.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 学生控制层
 *
 * @author Harry
 */
@RestController
@RequestMapping("v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(params = "action=get_all")
    public Result listStudent() {
        List<Student> students = studentService.listStudent();
        return new Result<>(students);
    }

}

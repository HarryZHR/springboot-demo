package com.zhr.student.controller;

import com.zhr.student.entity.Student;
import com.zhr.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(params = "action=get_all")
    public List<Student> listStudent(){
        return studentService.listStudent();
    }

    /**
     * 学生登陆操作
     * @param studentId 学生id
     * @param password 密码
     * @return 学生对象
     */
    @GetMapping(params = "action=student_login")
    public Student getStudent(@RequestParam(value = "studentId") String studentId, @RequestParam(value = "password") String password) {
        return studentService.getStudentByIdAndPassword(studentId, password);
    }
}

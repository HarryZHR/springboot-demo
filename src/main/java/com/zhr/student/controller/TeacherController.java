package com.zhr.student.controller;

import com.zhr.student.entity.Teacher;
import com.zhr.student.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 学生登陆操作
     * @param teacherId 学生id
     * @param password 密码
     * @return 学生对象
     */
    @GetMapping(params = "action=teacher_login")
    public Teacher getTeacher(@RequestParam(value = "teacherId") String teacherId, @RequestParam(value = "password") String password) {
        return teacherService.getTeacherByIdAndPassword(teacherId, password);
    }
}

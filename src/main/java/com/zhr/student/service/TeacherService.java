package com.zhr.student.service;

import com.zhr.student.entity.Teacher;

import java.util.List;

public interface TeacherService {

    /**
     * 教师登陆
     * @param teacherNum 教师工号
     * @param password 密码
     * @return 教师
     */
    Teacher getTeacherByNumAndPassword(String teacherNum, String password);

    /**
     * 获取本校所有老师
     * @return 教师集合
     */
    List<Teacher> listTeacher();
}

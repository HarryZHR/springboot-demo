package com.zhr.student.service;

import com.zhr.student.entity.Teacher;

public interface TeacherService {

    /**
     * 教师登陆
     * @param teacherNum 教师工号
     * @param password 密码
     * @return 教师
     */
    Teacher getTeacherByNumAndPassword(String teacherNum, String password);
}

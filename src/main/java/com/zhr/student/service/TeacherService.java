package com.zhr.student.service;

import com.zhr.student.entity.Teacher;

public interface TeacherService {

    /**
     * 教师登陆
     * @param teacherId 教师id
     * @param password 密码
     * @return 教师
     */
    Teacher getTeacherByIdAndPassword(String teacherId, String password);
}

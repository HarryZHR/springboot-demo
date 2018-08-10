package com.zhr.student.service;

import com.github.pagehelper.Page;
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

    /**
     * get all teachers in this school by page
     * @param teacherNum teacher's work id
     * @param teacherName teacher's name key
     * @param pageNo current page number
     * @param pageSize every page teacher's number
     * @return teacher collection
     */
    Page<Teacher> listTeacherByPage(String teacherNum, String teacherName, Integer pageNo, Integer pageSize);
}

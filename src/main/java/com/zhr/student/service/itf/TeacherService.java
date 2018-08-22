package com.zhr.student.service.itf;

import com.github.pagehelper.Page;
import com.zhr.student.entity.Teacher;

import java.util.List;

/**
 * 教师的服务层接口
 *
 * @author Harry
 */
public interface TeacherService {

    /**
     * 教师登陆
     *
     * @param teacherNum 教师工号
     * @param password   密码
     * @return 教师
     */
    Teacher getTeacherByNumAndPassword(String teacherNum, String password);

    /**
     * 获取本校所有老师
     *
     * @return 教师集合
     */
    List<Teacher> listTeacher();

    /**
     * get all teachers in this school by page
     *
     * @param teacherNum  teacher's work id
     * @param teacherName teacher's name key
     * @param pageType    sort type
     * @param pageNo      current page number
     * @param pageSize    every page teacher's number
     * @return teacher collection
     */
    Page<Teacher> listTeacherByPage(String teacherNum, String teacherName, String pageType, Integer pageNo, Integer pageSize);

    /**
     * 保存教师
     *
     * @param teacher 教师参数
     * @return 保存行数
     */
    Integer saveTeacher(Teacher teacher);

    /**
     * 通过id获取教师对象
     *
     * @param id 教师id
     * @return 教师对象
     */
    Teacher getTeacherById(Long id);

    /**
     * 更新教师信息
     *
     * @param teacher 教师的新信息
     * @return 数据库影响行数
     */
    Integer updateTeacher(Teacher teacher);
}

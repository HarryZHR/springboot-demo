package com.zhr.student.service.itf;

import com.github.pagehelper.Page;
import com.zhr.student.entity.Student;

import java.util.List;

/**
 * 学生的服务层接口
 *
 * @author Harry
 */
public interface StudentService {
    /**
     * 分页搜索获取学生列表
     *
     * @param studentNum  学生学号
     * @param studentName 学生姓名
     * @param clazzId     班级id
     * @param pageNo      当前页
     * @param pageSize    一页记录数
     * @return 学生的集合
     */
    Page<Student> listStudentByPage(String studentNum, String studentName, Long clazzId, Integer pageNo, Integer pageSize);

    /**
     * 保存学生
     *
     * @param student student
     * @return 保存了几条记录
     */
    Integer saveStudent(Student student);

    /**
     * 通过id获取学生对象
     *
     * @param studentId 学生id
     * @return 学生对象
     */
    Student getStudentOne(Long studentId);

    /**
     * 更新一条学生信息
     *
     * @param student 学生参数
     * @return 影响行数
     */
    Integer updateStudent(Student student);

    /**
     * 获取一个学生所在年级的所有班级
     *
     * @param student 学生
     * @return 班级号
     */
    List<Integer> listClazzNumByStudent(Student student);

}

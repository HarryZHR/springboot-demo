package com.zhr.student.service;

import com.github.pagehelper.Page;
import com.zhr.student.entity.Clazz;

import java.util.List;

/**
 * 班级的服务层接口
 *
 * @author Harry
 */
public interface ClazzService {

    /**
     * 分页获取班级
     *
     * @param grade           年级号
     * @param clazzNum        班级号
     * @param headTeacherName 班主任姓名
     * @param pageNo          页码
     * @param pageSize        一页的条数
     * @return 分页情况
     */
    Page<Clazz> listClazzByPage(Integer grade, Integer clazzNum, String headTeacherName, Integer pageNo, Integer pageSize);

    /**
     * 获取所有的年级号
     *
     * @return 年级集合
     */
    List<String> listGradeAll();

    /**
     * 获取所有的班级号
     *
     * @return 班级集合
     */
    List<Integer> listClazzNumAll();

    /**
     * 批量插入班级
     *
     * @param clazz       保存的班级参数
     * @param endClazzNum 结束班级号
     * @return 返回插入记录数
     */
    Integer saveClazzList(Clazz clazz, Integer endClazzNum);

    /**
     * 通过id获取班级对象
     *
     * @param id 班级id
     * @return 班级对象
     */
    Clazz getClazzById(Long id);

    /**
     * 查找教师担任班主任的班级
     *
     * @param headTeacherId 教师id
     * @return 班级
     */
    Clazz getClazzByHeadTeacher(Long headTeacherId);

    /**
     * 更新班级信息
     *
     * @param clazz 班级的信息
     * @return 影响行数
     */
    Integer updateClazz(Clazz clazz);
}

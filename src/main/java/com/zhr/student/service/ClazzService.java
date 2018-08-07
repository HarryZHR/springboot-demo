package com.zhr.student.service;

import com.github.pagehelper.Page;
import com.zhr.student.entity.Clazz;

import java.util.List;


public interface ClazzService {

    /**
     * 分页获取班级
     * @param grade 年级号
     * @param clazzNum 班级号
     * @param headTeacher 班主任
     * @param pageNo 页码
     * @param pageSize 一页的条数
     * @return 分页情况
     */
    Page<Clazz> listClazzByPage(Integer grade, Integer clazzNum, String headTeacher, Integer pageNo, Integer pageSize);

    /**
     * 获取所有的年级号
     * @return 年级集合
     */
    List<Integer> listGradeAll();

    /**
     * 获取所有的班级号
     * @return 班级集合
     */
    List<Integer> listClazzNumAll();

    /**
     * 保存一个班级对象
     * @param clazz 班级的参数
     */
Integer saveClazz(Clazz clazz);

    /**
     * 通过年级和班级号确定一个班级
     * @param grade 年级
     * @param clazzNum 班级号
     * @return 班级
     */
    Clazz getClazzByGradeAndClazzNum(Integer grade, Integer clazzNum);
}

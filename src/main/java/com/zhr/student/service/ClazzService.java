package com.zhr.student.service;

import com.github.pagehelper.Page;
import com.zhr.student.entity.Clazz;


public interface ClazzService {


    Page<Clazz> listClazzByPage(Integer grade, Integer clazzNum, String headTeacher, String type, Integer pageNo, Integer pageSize);
}

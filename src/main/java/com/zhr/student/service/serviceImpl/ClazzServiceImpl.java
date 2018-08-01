package com.zhr.student.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhr.student.entity.Clazz;
import com.zhr.student.repository.ClazzRepository;
import com.zhr.student.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Resource
    private ClazzRepository clazzRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<Clazz> listClazzByPage(Integer grade, Integer clazzNum, String headTeacher, String type, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return clazzRepository.listClazzByPage(grade, clazzNum, headTeacher, type);
    }
}

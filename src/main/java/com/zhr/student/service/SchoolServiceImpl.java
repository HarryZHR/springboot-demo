package com.zhr.student.service;

import com.zhr.student.dao.SchoolDAO;
import com.zhr.student.entity.School;
import com.zhr.student.service.itf.SchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 学校的服务层
 *
 * @author Harry
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Resource
    private SchoolDAO schoolDAO;

    @Override
    public School getSchoolById(Long schoolId) {
        return schoolDAO.getSchoolById(schoolId);
    }
}

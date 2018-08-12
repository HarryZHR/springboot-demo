package com.zhr.student.service.serviceImpl;

import com.zhr.student.entity.School;
import com.zhr.student.dao.SchoolDAO;
import com.zhr.student.service.SchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Resource
    private SchoolDAO schoolDAO;

    @Override
    public School getSchoolById(Long schoolId) {
        return schoolDAO.getSchoolById(schoolId);
    }
}

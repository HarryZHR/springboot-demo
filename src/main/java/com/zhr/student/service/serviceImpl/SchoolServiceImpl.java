package com.zhr.student.service.serviceImpl;

import com.zhr.student.entity.School;
import com.zhr.student.repository.SchoolRepository;
import com.zhr.student.service.SchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Resource
    private SchoolRepository schoolRepository;

    @Override
    public School getSchoolById(String schoolId) {
        return schoolRepository.getSchoolById(schoolId);
    }
}

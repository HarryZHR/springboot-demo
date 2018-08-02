package com.zhr.student.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.School;
import com.zhr.student.repository.ClazzRepository;
import com.zhr.student.repository.SchoolRepository;
import com.zhr.student.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Resource
    private ClazzRepository clazzRepository;

    @Resource
    private SchoolRepository schoolRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<Clazz> listClazzByPage(Integer grade, Integer clazzNum, String headTeacher, String type, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return clazzRepository.listClazzByPage(grade, clazzNum, headTeacher, type);
    }

    @Override
    public List<Integer> listGradeAll() {
        School school = schoolRepository.getSchoolById("324234234");
        return clazzRepository.listGradeAll(school.getSchoolId());
    }

    @Override
    public List<Integer> listClazzNumAll() {
        School school = schoolRepository.getSchoolById("324234234");
        return clazzRepository.listClazzNumAll(school.getSchoolId());
    }
}

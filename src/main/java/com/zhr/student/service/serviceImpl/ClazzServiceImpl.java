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
    public Page<Clazz> listClazzByPage(Integer grade, Integer clazzNum, String headTeacher, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return clazzRepository.listClazzByPage(grade, clazzNum, headTeacher);
    }

    @Override
    public List<Integer> listGradeAll() {
        School school = schoolRepository.getSchoolById(324234234L);
        return clazzRepository.listGradeAll(school.getSchoolId());
    }

    @Override
    public List<Integer> listClazzNumAll() {
        School school = schoolRepository.getSchoolById(324234234L);
        return clazzRepository.listClazzNumAll(school.getSchoolId());
    }

    @Override
    public void saveClazz(Clazz clazz) {
        clazz.setDeleteFlag(true);
        clazz.setSchool(schoolRepository.getSchoolById(324234234L));
        clazzRepository.saveClazz(clazz);
    }

    @Override
    public Clazz getClazzByGradeAndClazzNum(Integer grade, Integer clazzNum) {

        return clazzRepository.getClazzByGradeAndClazzNum(grade, clazzNum, 324234234L);
    }
}

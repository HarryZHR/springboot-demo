package com.zhr.student.service.serviceImpl;

import afu.org.checkerframework.checker.igj.qual.I;
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
import java.util.ArrayList;
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
    public Integer saveClazz(Clazz clazz) {
        if (clazzRepository.findClazzByGradeAndClazzNum(clazz.getGrade(), clazz.getClazzNum(),324234234L) != null){
            return 0;
        } else {
            clazz.setDeleteFlag(true);
            clazz.setSchool(schoolRepository.getSchoolById(324234234L));
            return clazzRepository.saveClazz(clazz);
        }
    }

    @Override
    public Clazz getClazzByGradeAndClazzNum(Integer grade, Integer clazzNum) {
        return clazzRepository.findClazzByGradeAndClazzNum(grade, clazzNum, 324234234L);
    }

    @Override
    public Integer saveClazzList(Clazz clazz, Integer endClazzNum) {
        if (endClazzNum == null) {
            endClazzNum = clazz.getClazzNum();
        }
        List<Clazz> clazzList = new ArrayList<>();
        for (int i = clazz.getClazzNum(); i <= endClazzNum; i++) {
            Clazz dataClazz = clazzRepository.findClazzByGradeAndClazzNum(clazz.getGrade(), i, 324234234L);
            if (dataClazz == null) {
                Clazz newClazz = new Clazz();
                newClazz.setGrade(clazz.getGrade());
                newClazz.setClazzNum(i);
                newClazz.setDeleteFlag(true);
                newClazz.setHeadTeacher(clazz.getHeadTeacher());
                newClazz.setSchool(schoolRepository.getSchoolById(324234234L));
                clazzList.add(newClazz);
            }
        }
        if (clazzList.size() > 0) {
            return clazzRepository.saveClazzList(clazzList);
        } else {
            return 0;
        }
    }

    public Clazz getClazzById(Long id){
        return clazzRepository.findClazzById(id);
    }
}

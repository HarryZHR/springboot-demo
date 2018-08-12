package com.zhr.student.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.School;
import com.zhr.student.dao.ClazzDAO;
import com.zhr.student.dao.SchoolDAO;
import com.zhr.student.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Resource
    private ClazzDAO clazzDAO;

    @Resource
    private SchoolDAO schoolDAO;

    @Transactional(readOnly = true)
    @Override
    public Page<Clazz> listClazzByPage(Integer grade, Integer clazzNum, String headTeacher, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return clazzDAO.listClazzByPage(grade, clazzNum, headTeacher);
    }

    @Override
    public List<Integer> listGradeAll() {
        School school = schoolDAO.getSchoolById(324234234L);
        return clazzDAO.listGradeAll(school.getSchoolId());
    }

    @Override
    public List<Integer> listClazzNumAll() {
        School school = schoolDAO.getSchoolById(324234234L);
        return clazzDAO.listClazzNumAll(school.getSchoolId());
    }

    @Override
    public Integer saveClazz(Clazz clazz) {
        if (clazzDAO.findClazzByGradeAndClazzNum(clazz.getGrade(), clazz.getClazzNum(),324234234L) != null){
            return 0;
        } else {
            clazz.setDeleteFlag(true);
            clazz.setSchool(schoolDAO.getSchoolById(324234234L));
            return clazzDAO.saveClazz(clazz);
        }
    }

    @Override
    public Clazz getClazzByGradeAndClazzNum(Integer grade, Integer clazzNum) {
        return clazzDAO.findClazzByGradeAndClazzNum(grade, clazzNum, 324234234L);
    }

    @Override
    public Integer saveClazzList(Clazz clazz, Integer endClazzNum) {
        if (endClazzNum == null) {
            endClazzNum = clazz.getClazzNum();
        }
        List<Clazz> clazzList = new ArrayList<>();
        for (int i = clazz.getClazzNum(); i <= endClazzNum; i++) {
            Clazz dataClazz = clazzDAO.findClazzByGradeAndClazzNum(clazz.getGrade(), i, 324234234L);
            if (dataClazz == null) {
                Clazz newClazz = new Clazz();
                newClazz.setGrade(clazz.getGrade());
                newClazz.setClazzNum(i);
                newClazz.setDeleteFlag(true);
                newClazz.setHeadTeacher(clazz.getHeadTeacher());
                newClazz.setSchool(schoolDAO.getSchoolById(324234234L));
                clazzList.add(newClazz);
            }
        }
        if (clazzList.size() > 0) {
            return clazzDAO.saveClazzList(clazzList);
        } else {
            return 0;
        }
    }

    public Clazz getClazzById(Long id){
        return clazzDAO.findClazzById(id);
    }
}

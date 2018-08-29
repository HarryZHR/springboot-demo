package com.zhr.student.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhr.student.common.util.ClazzGradeUtils;
import com.zhr.student.dao.ClazzDAO;
import com.zhr.student.dao.ClazzStudentDAO;
import com.zhr.student.dao.SchoolDAO;
import com.zhr.student.dao.StudentDAO;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.ClazzStudent;
import com.zhr.student.entity.School;
import com.zhr.student.entity.Student;
import com.zhr.student.service.itf.ClazzService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 班级的服务层
 *
 * @author Harry
 */
@Service
public class ClazzServiceImpl implements ClazzService {

    @Resource
    private ClazzDAO clazzDAO;

    @Resource
    private SchoolDAO schoolDAO;

    @Resource
    private ClazzStudentDAO clazzStudentDAO;

    @Resource
    private StudentDAO studentDAO;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Page<Clazz> listClazzByPage(Integer grade, Integer clazzNum, String headTeacherName, Integer pageNo, Integer pageSize) {
        if (StringUtils.isBlank(headTeacherName)) {
            headTeacherName = null;
        } else {
            headTeacherName = "%" + headTeacherName + "%";
        }
        PageHelper.startPage(pageNo, pageSize);
        return clazzDAO.listClazzByPage(grade, clazzNum, headTeacherName);
    }

    @Override
    public List<String> listGradeAll() {
        School school = schoolDAO.getSchoolById(324234234L);
        List<Integer> grades = clazzDAO.listGradeAll(school.getSchoolId());
        return grades.stream().map(ClazzGradeUtils::getGrade).collect(Collectors.toList());
    }

    @Override
    public List<Integer> listClazzNumAll() {
        School school = schoolDAO.getSchoolById(324234234L);
        return clazzDAO.listClazzNumAll(school.getSchoolId());
    }

    @Override
    public Integer saveClazzList(Clazz clazz, Integer endClazzNum) {
        if (endClazzNum == null) {
            endClazzNum = clazz.getClazzNum();
        }
        List<Clazz> clazzList = new ArrayList<>();
        for (int i = clazz.getClazzNum(); i <= endClazzNum; i++) {
            Clazz dataClazz = clazzDAO.findClazzByGradeAndClazzNum(clazz.getGrade(), i, clazz.getType().toString(), 324234234L);
            if (dataClazz == null) {
                Clazz newClazz = new Clazz();
                newClazz.setGrade(clazz.getGrade());
                newClazz.setType(clazz.getType());
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

    @Override
    public Clazz getClazzById(Long id) {
        return clazzDAO.findClazzById(id);
    }

    @Override
    public Clazz getClazzByHeadTeacher(Long headTeacherId) {
        return clazzDAO.findClazzByHeadTeacher(headTeacherId);
    }

    @Override
    public Integer updateClazz(Clazz clazz) {
        Clazz origin = clazzDAO.findClazzById(clazz.getClazzId());
        origin.setHeadTeacher(clazz.getHeadTeacher());
        return clazzDAO.updateClazz(origin);
    }

    @Override
    public Integer saveClazzStudent(Long studentId, Long clazzId) {
        ClazzStudent clazzStudent = new ClazzStudent();
        Student student = studentDAO.findOneByStudentId(studentId);
        Clazz clazz = clazzDAO.findClazzById(clazzId);
        clazzStudent.setStudent(student);
        clazzStudent.setClazzNum(clazz.getClazzNum());
        return clazzStudentDAO.saveStudentClazz(clazzStudent);
    }
}

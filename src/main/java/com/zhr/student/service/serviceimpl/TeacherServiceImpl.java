package com.zhr.student.service.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhr.student.dao.SchoolDAO;
import com.zhr.student.dao.TeacherDAO;
import com.zhr.student.entity.School;
import com.zhr.student.entity.Teacher;
import com.zhr.student.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师的服务层
 *
 * @author Harry
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherDAO teacherDAO;

    @Resource
    private SchoolDAO schoolDAO;

    @Override
    public Teacher getTeacherByNumAndPassword(String teacherNum, String password) {
        return teacherDAO.getTeacherByNumAndPassword(teacherNum, password);
    }

    @Override
    public List<Teacher> listTeacher() {
        School school = schoolDAO.getSchoolById(324234234L);
        return teacherDAO.listTeacher(school.getSchoolId());
    }

    @Override
    public Page<Teacher> listTeacherByPage(String teacherNum, String teacherName, String pageType, Integer pageNo, Integer pageSize) {
        if (StringUtils.isBlank(teacherName)) {
            teacherName = null;
        } else {
            teacherName = "%" + teacherName + "%";
        }
        if (StringUtils.isBlank(teacherNum)) {
            teacherNum = null;
        }
        PageHelper.startPage(pageNo, pageSize);
        return teacherDAO.listTeacherByPage(teacherNum, teacherName, pageType, 324234234L);
    }

    @Override
    public Integer saveTeacher(Teacher teacher) {
        teacher.setIdentity(Teacher.TeacherIdentity.valueOf("teacher"));
        teacher.setPassword("123456");
        teacher.setSchool(schoolDAO.getSchoolById(324234234L));
        teacher.setDeleteFlag(true);
        return teacherDAO.saveTeacher(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherDAO.getTeacherById(id);
    }

    @Override
    public Integer updateTeacher(Teacher teacher) {
        Teacher origin = teacherDAO.getTeacherById(teacher.getTeacherId());
        if (teacher.getBirthday() != null) {
            origin.setBirthday(teacher.getBirthday());
        }
        origin.setGender(teacher.getGender());
        origin.setTeacherName(teacher.getTeacherName());
        return teacherDAO.updateTeacher(origin);
    }
}

package com.zhr.student.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhr.student.entity.School;
import com.zhr.student.entity.Teacher;
import com.zhr.student.dao.SchoolDAO;
import com.zhr.student.dao.TeacherDAO;
import com.zhr.student.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public Page<Teacher> listTeacherByPage(String teacherNum, String teacherName, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return teacherDAO.listTeacherByPage(teacherNum, teacherName);
    }

    @Override
    public Integer saveTeacher(Teacher teacher) {
        teacher.setIdentity(Teacher.TeacherIdentity.valueOf("teacher"));
        teacher.setPassword("123456");
        teacher.setSchool(schoolDAO.getSchoolById(324234234L));
        return teacherDAO.saveTeacher(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherDAO.getTeacherById(id);
    }
}

package com.zhr.student.service.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhr.student.dao.StudentDAO;
import com.zhr.student.entity.School;
import com.zhr.student.entity.Student;
import com.zhr.student.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 学生的服务层
 *
 * @author Harry
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDAO studentDAO;

    @Override
    public Page<Student> listStudentByPage(String studentNum, String studentName, Long clazzId, Integer pageNo, Integer pageSize) {
        if (StringUtils.isBlank(studentName)) {
            studentName = null;
        } else {
            studentName = "%" + studentName + "%";
        }
        PageHelper.startPage(pageNo, pageSize);
        return studentDAO.findAllStudent(studentNum, studentName, clazzId, 324234234L);
    }

    @Override
    public Integer saveStudent(Student student) {
        student.setDeleteFlag(true);
        School school = new School();
        school.setSchoolId(324234234L);
        student.setSchool(school);
        student.setPassword("123456");
        return studentDAO.saveStudent(student);
    }
}
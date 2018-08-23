package com.zhr.student.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhr.student.dao.StudentDAO;
import com.zhr.student.entity.School;
import com.zhr.student.entity.Student;
import com.zhr.student.service.itf.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        return studentDAO.findAllStudent(studentNum, studentName, 324234234L);
    }

    @Override
    public Integer saveStudent(Student student) {
        student.setDeleteFlag(true);
        School school = new School();
        school.setSchoolId(324234234L);
        student.setSchool(school);
        List<Student> students = studentDAO.findAllByNo(student.getStudentNo(), 324234234L);
        if (students == null || students.size() == 0) {
            student.setPassword("123456");
            return studentDAO.saveStudent(student);
        }
        return -1;
    }

    @Override
    public Student getStudentOne(Long studentId) {
        return studentDAO.findOneByStudentId(studentId);
    }

    @Override
    public Integer updateStudent(Student student) {
        Student origin = studentDAO.findOneByStudentId(student.getStudentId());
        if (origin == null) {
            return -1;
        }
        origin.setStartYear(student.getStartYear());
        origin.setStudentName(student.getStudentName());
        origin.setGender(student.getGender());
        origin.setBirthday(student.getBirthday());
        return studentDAO.updateStudent(origin);
    }

}
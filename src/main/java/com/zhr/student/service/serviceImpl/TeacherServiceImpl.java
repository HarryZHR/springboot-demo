package com.zhr.student.service.serviceImpl;

import com.zhr.student.entity.School;
import com.zhr.student.entity.Teacher;
import com.zhr.student.repository.SchoolRepository;
import com.zhr.student.repository.TeacherRepository;
import com.zhr.student.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherRepository teacherRepository;

    @Resource
    private SchoolRepository schoolRepository;

    @Override
    public Teacher getTeacherByNumAndPassword(String teacherNum, String password) {
        return teacherRepository.getTeacherByNumAndPassword(teacherNum, password);
    }

    @Override
    public List<Teacher> listTeacher() {
        School school = schoolRepository.getSchoolById(324234234L);
        return teacherRepository.listTeacher(school.getSchoolId());
    }
}

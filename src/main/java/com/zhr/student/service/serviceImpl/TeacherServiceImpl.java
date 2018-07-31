package com.zhr.student.service.serviceImpl;

import com.zhr.student.entity.Teacher;
import com.zhr.student.repository.TeacherRepository;
import com.zhr.student.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherRepository teacherRepository;


    @Override
    public Teacher getTeacherByIdAndPassword(String teacherId, String password) {
        return teacherRepository.getTeacherByIdAndPassword(teacherId, password);
    }
}

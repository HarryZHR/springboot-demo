package com.zhr.student.service.serviceImpl;

import com.zhr.student.entity.Student;
import com.zhr.student.repository.StudentRepository;
import com.zhr.student.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentRepository studentRepository;

    @Override
    public List<Student> listStudent() {
        return studentRepository.listStudent();
    }

    @Override
    public Student getStudentByIdAndPassword(String studentId, String password) {
        return studentRepository.getStudentByIdAndPassword(studentId, password);
    }
}

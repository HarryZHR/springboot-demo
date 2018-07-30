package com.zhr.student.service.serviceImpl;

import com.zhr.student.entity.Student;
import com.zhr.student.repository.StudentRepository;
import com.zhr.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> listStudent() {
        return studentRepository.listStudent();
    }
}

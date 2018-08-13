package com.zhr.student.service.serviceImpl;

import com.zhr.student.dao.StudentDAO;
import com.zhr.student.entity.Student;
import com.zhr.student.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDAO studentDAO;

    @Override
    public List<Student> listStudent() {
        return studentDAO.listStudent();
    }

}

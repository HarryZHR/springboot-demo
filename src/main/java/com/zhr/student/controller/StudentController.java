package com.zhr.student.controller;

import com.github.pagehelper.Page;
import com.zhr.student.common.result.Result;
import com.zhr.student.dto.student.StudentDTO;
import com.zhr.student.entity.Student;
import com.zhr.student.service.itf.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 学生控制层
 *
 * @author Harry
 */
@RestController
@RequestMapping("v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(params = "action=get_list")
    public Result listStudent(@RequestParam(value = "studentName", required = false) String studentName,
                              @RequestParam(value = "studentNum", required = false) String studentNum,
                              @RequestParam(value = "clazzId", required = false) Long clazzId,
                              @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Page<Student> studentPage = studentService.listStudentByPage(studentName, studentNum, clazzId, pageNo, pageSize);
        List<StudentDTO> studentDTOS = studentPage.stream().map(student -> new StudentDTO().convertFrom(student)).collect(Collectors.toList());
        return new Result<>(studentDTOS, studentPage.getPages());
    }

    @PostMapping(params = "action=save_one")
    public Result saveStudent(@RequestBody StudentDTO studentDTO) {
        System.out.println(111);
        Map<String, Integer> res = new HashMap<>(1);
        res.put("colNum", studentService.saveStudent(studentDTO.convertTo()));
        return new Result<>(res);
    }
}

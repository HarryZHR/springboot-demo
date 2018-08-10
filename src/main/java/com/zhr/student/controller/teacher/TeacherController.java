package com.zhr.student.controller.teacher;

import com.github.pagehelper.Page;
import com.zhr.student.common.MyPage;
import com.zhr.student.entity.Teacher;
import com.zhr.student.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 教师登陆操作
     * @param teacherNum 教师id
     * @param password 密码
     * @return 教师对象
     */
    @GetMapping(params = "action=teacher_login")
    public Teacher getTeacher(@RequestParam(value = "teacherNum") String teacherNum, @RequestParam(value = "password") String password) {
        return teacherService.getTeacherByNumAndPassword(teacherNum, password);
    }

    /**
     * 获取学校所有的教师
     * @return 教师的集合
     */
    @GetMapping(params = "action=get_all")
    public List<TeacherSelectDTO> getTeacherAll() {
        List<Teacher> teachers = teacherService.listTeacher();
        return teachers.stream().map(teacher -> new TeacherSelectDTO().convertFrom(teacher)).collect(Collectors.toList());
    }

    @GetMapping(params = "action=get_page")
    public MyPage getTeacherPage(@RequestParam(value = "teacherNum",required = false) String teacherNum,
                                                   @RequestParam(value = "teacherName",required = false) String teacherName,
                                                   @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (StringUtils.isBlank(teacherName)) {
            teacherName = null;
        } else {
            teacherName = "%" + teacherName + "%";
        }
        Page<Teacher> teacherPage = teacherService.listTeacherByPage(teacherNum, teacherName, pageNo, pageSize);
        List<TeacherSelectDTO> teacherDTOS = teacherPage.stream().map(teacher -> new TeacherSelectDTO().convertFrom(teacher)).collect(Collectors.toList());
        return new MyPage(teacherPage, teacherDTOS);
    }
}

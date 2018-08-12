package com.zhr.student.controller.teacher;

import com.github.pagehelper.Page;
import com.zhr.student.common.MyPage;
import com.zhr.student.entity.Teacher;
import com.zhr.student.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /**
     * 分页获取教师的列表
     * @param teacherNum 教师的工号
     * @param teacherName 教师的姓名
     * @param pageNo 页码
     * @param pageSize 一页的记录数
     * @return 教师的分页结果
     */
    @GetMapping(params = "action=get_page")
    public MyPage getTeacherPage(@RequestParam(value = "teacherNum",required = false) String teacherNum,
                                 @RequestParam(value = "teacherName",required = false) String teacherName,
                                 @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (StringUtils.isBlank(teacherName)) {
            teacherName = null;
        } else {
            teacherName = "%" + teacherName + "%";
        }
        if (StringUtils.isBlank(teacherNum)) {
            teacherNum = null;
        }
        Page<Teacher> teacherPage = teacherService.listTeacherByPage(teacherNum, teacherName, pageNo, pageSize);
        List<TeacherSaveDTO> teacherDTOS = teacherPage.stream().map(teacher -> new TeacherSaveDTO().convertFrom(teacher)).collect(Collectors.toList());
             return new MyPage(teacherPage, teacherDTOS);
    }

    /**
     * 保存教师对象
     * @param teacherSaveDTO 前端传入的参数
     * @return 保存结果
     */
    @PostMapping(params = "action=save_one")
    public Map<String, Integer> saveTeacher(@RequestBody TeacherSaveDTO teacherSaveDTO) {
        Map<String, Integer> resultMap = new HashMap<>();
    resultMap.put("colNum", teacherService.saveTeacher(teacherSaveDTO.convertTo()));
        return resultMap;
    }

    /**
     * 通过id获取教师
     * @param teacherId 教师id
     * @return 教师DTO
     */
    @GetMapping(value = "/{teacherId}", params = "action=get_one")
    public TeacherSaveDTO getTeacher(@PathVariable Long teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        return new TeacherSaveDTO().convertFrom(teacher);
    }
}

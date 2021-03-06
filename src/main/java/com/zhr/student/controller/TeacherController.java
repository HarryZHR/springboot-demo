package com.zhr.student.controller;

import com.github.pagehelper.Page;
import com.zhr.student.common.result.Result;
import com.zhr.student.common.util.ClazzGradeUtils;
import com.zhr.student.dto.teacher.TeacherSaveDTO;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.Teacher;
import com.zhr.student.service.itf.ClazzService;
import com.zhr.student.service.itf.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 教师的控制层
 *
 * @author Harry
 */
@RestController
@RequestMapping("v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    private final ClazzService clazzService;

    @Autowired
    public TeacherController(TeacherService teacherService, ClazzService clazzService) {
        this.teacherService = teacherService;
        this.clazzService = clazzService;
    }


    /**
     * 教师登陆操作
     *
     * @param teacherNum 教师id
     * @param password   密码
     * @return 教师对象
     */
    @GetMapping(params = "action=teacher_login")
    public Result getTeacher(@RequestParam(value = "teacherNum") String teacherNum, @RequestParam(value = "password") String password) {
        Teacher teacher = teacherService.getTeacherByNumAndPassword(teacherNum, password);
        return new Result<>(teacher);
    }

    /**
     * 获取学校所有的教师
     *
     * @return 教师的集合
     */
    @GetMapping(params = "action=get_all")
    public Result getTeacherAll() {
        List<Teacher> teachers = teacherService.listTeacher();
        List<TeacherSaveDTO> teacherSaveDTOS = teachers.stream().map(teacher -> new TeacherSaveDTO().convertFrom(teacher)).collect(Collectors.toList());
        return new Result<>(teacherSaveDTOS);
    }

    /**
     * 分页获取教师的列表
     *
     * @param teacherNum  教师的工号
     * @param teacherName 教师的姓名
     * @param pageType    获取的结果排序类型
     * @param pageNo      页码
     * @param pageSize    一页的记录数
     * @return 教师的分页结果
     */
    @GetMapping(params = "action=get_page")
    public Result getTeacherPage(@RequestParam(value = "teacherNum", required = false) String teacherNum,
                                 @RequestParam(value = "teacherName", required = false) String teacherName,
                                 @RequestParam(value = "pageType", required = false) String pageType,
                                 @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        Page<Teacher> teacherPage = teacherService.listTeacherByPage(teacherNum, teacherName, pageType, pageNo, pageSize);
        List<TeacherSaveDTO> teacherDTOS = teacherPage.stream().map(teacher -> {
            TeacherSaveDTO dto = new TeacherSaveDTO().convertFrom(teacher);
            Clazz clazz = clazzService.getClazzByHeadTeacher(teacher.getTeacherId());
            if (clazz != null) {
                dto.setClazzName(ClazzGradeUtils.getClazzName(clazz));
            }
            return dto;
        }).collect(Collectors.toList());
        return new Result<>(teacherDTOS, teacherPage.getPages());
    }

    /**
     * 保存教师对象
     *
     * @param teacherSaveDTO 前端传入的参数
     * @return 保存结果
     */
    @PostMapping(params = "action=save_one")
    public Result saveTeacher(@RequestBody TeacherSaveDTO teacherSaveDTO) {
        return new Result<>(teacherService.saveTeacher(teacherSaveDTO.convertTo()));
    }

    /**
     * 通过id获取教师
     *
     * @param teacherId 教师id
     * @return 教师DTO
     */
    @GetMapping(value = "/{teacherId}", params = "action=get_one")
    public Result getTeacher(@PathVariable Long teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        return new Result<>(new TeacherSaveDTO().convertFrom(teacher));
    }

    /**
     * 修改教师信息
     *
     * @param teacherId 教师的id
     * @param dto 教师的信息
     * @return 修改了几条
     */
    @PutMapping(value = "/{teacherId}", params = "action=update_one")
    public Result updateTeacher(@PathVariable Long teacherId,
                                @RequestBody TeacherSaveDTO dto) {
        Teacher teacher = dto.convertTo();
        teacher.setTeacherId(teacherId);
        return new Result<>(teacherService.updateTeacher(teacher));
    }
}

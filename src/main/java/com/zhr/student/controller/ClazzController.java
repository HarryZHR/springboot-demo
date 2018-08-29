package com.zhr.student.controller;

import com.github.pagehelper.Page;
import com.zhr.student.common.result.Result;
import com.zhr.student.dto.clazz.ClazzDTO;
import com.zhr.student.dto.clazz.ClazzInfoDTO;
import com.zhr.student.dto.clazz.ClazzUpdateDTO;
import com.zhr.student.entity.Clazz;
import com.zhr.student.service.itf.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 班级的控制层
 *
 * @author Harry
 */
@RestController
@RequestMapping("v1/clazz")
public class ClazzController {

    private final ClazzService clazzService;

    @Autowired
    public ClazzController(ClazzService clazzService) {
        this.clazzService = clazzService;
    }

    @GetMapping(params = "action=get_all_page")
    public Result listStudent(@RequestParam(value = "grade", required = false) Integer grade,
                              @RequestParam(value = "clazzNum", required = false) Integer clazzNum,
                              @RequestParam(value = "headTeacherName", required = false) String headTeacherName,
                              @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        Page<Clazz> clazzPage = clazzService.listClazzByPage(grade, clazzNum, headTeacherName, pageNo, pageSize);
        List<ClazzDTO> clazzDTOS = clazzPage.stream().map(clazz -> new ClazzDTO().convertFrom(clazz)).collect(Collectors.toList());
        return new Result<>(clazzDTOS, clazzPage.getPages());
    }

    /**
     * 获取年级号和班级号
     *
     * @return 年级号和班级号
     */
    @GetMapping(params = "action=get_clazz_info")
    public Result getClazzInfo() {
        List<String> grades = clazzService.listGradeAll();
        List<Integer> clazzNums = clazzService.listClazzNumAll();
        ClazzInfoDTO clazzInfoDTO = new ClazzInfoDTO();
        clazzInfoDTO.setClazzNums(clazzNums);
        clazzInfoDTO.setGrades(grades);
        return new Result<>(clazzInfoDTO);
    }

    /**
     * 批量保存班级实体
     */
    @PostMapping(params = "action=save_list")
    public Result saveClazzList(@RequestBody ClazzDTO clazzDTO) {
        return new Result<>(clazzService.saveClazzList(clazzDTO.convertTo(), clazzDTO.getEndClazzNum()));
    }

    /**
     * 获取一个班级的详情
     *
     * @param clazzId 班级id
     * @return 班级实体
     */
    @GetMapping(value = "/{clazzId}")
    public Result getClazz(@PathVariable Long clazzId) {
        ClazzDTO clazzDTO = new ClazzDTO().convertFrom(clazzService.getClazzById(clazzId));
        return new Result<>(clazzDTO);
    }

    /**
     * 更新班级信息
     *
     * @param clazzId  班级的id
     * @param clazzDTO 班级的信息
     * @return 更新了多少条
     */
    @PutMapping(value = "/{clazzId}")
    public Result updateClazz(@PathVariable Long clazzId,
                              @RequestBody ClazzUpdateDTO clazzDTO) {
        Clazz clazz = clazzDTO.convertTo();
        clazz.setClazzId(clazzId);
        return new Result<>(clazzService.updateClazz(clazz));
    }

    @PostMapping(params = "action=save_clazz_student")
    public Result saveClazzStudent(@RequestParam(value = "studentId") Long studentId,
                                   @RequestParam(value = "clazzId") Long clazzId) {
        return new Result<>(clazzService.saveClazzStudent(studentId, clazzId));
    }
}

package com.zhr.student.controller.clazz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhr.student.common.MyPage;
import com.zhr.student.entity.Clazz;
import com.zhr.student.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping(params = "action=get_all_page")
    public MyPage listStudent(@RequestParam(value = "grade", required = false) Integer grade,
                                        @RequestParam(value = "clazzNum", required = false) Integer clazzNum,
                                        @RequestParam(value = "headTeacherName", required = false) String headTeacherName,
                                        @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                        @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if ("".equals(headTeacherName)) {
            headTeacherName = null;
        } else if (headTeacherName != null){
            headTeacherName = "%" + headTeacherName + "%";
        }
        Page<Clazz> clazzPage =  clazzService.listClazzByPage(grade, clazzNum, headTeacherName, pageNo, pageSize);
        List<ClazzDTO> clazzDTOS = clazzPage.stream().map(clazz -> new ClazzDTO().convertFrom(clazz)).collect(Collectors.toList());
        return new MyPage(clazzPage, clazzDTOS);
    }

    /**
     * 获取年级号和班级号
     * @return 年级号和班级号
     */
    @GetMapping(params = "action=get_clazz_info")
    public ClazzInfoDTO getClazzInfo() {
        List<Integer> grades = clazzService.listGradeAll();
        List<Integer> clazzNums = clazzService.listClazzNumAll();
        ClazzInfoDTO clazzInfoDTO = new ClazzInfoDTO();
        clazzInfoDTO.setClazzNums(clazzNums);
        clazzInfoDTO.setGrades(grades);
        return clazzInfoDTO;
    }

    /**
     * 批量保存班级实体
     */
    @PostMapping(params = "action=save_list")
    public Map<String, Integer> saveClazzList(@RequestBody ClazzDTO clazzDTO) {
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("colNum",clazzService.saveClazzList(clazzDTO.convertTo(), clazzDTO.getEndClazzNum()));
        return resultMap;
    }

    /**
     * 获取一个班级的详情
     * @param clazzId 班级id
     * @return 班级实体
     */
    @GetMapping(value="/{clazzId}")
    public ClazzDTO getClazz(@PathVariable Long clazzId){
        return new ClazzDTO().convertFrom(clazzService.getClazzById(clazzId));
    }

}

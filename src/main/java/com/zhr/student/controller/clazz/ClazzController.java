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
    public MyPage<ClazzDTO> listStudent(@RequestParam(value = "grade", required = false) Integer grade,
                                        @RequestParam(value = "clazzNum", required = false) Integer clazzNum,
                                        @RequestParam(value = "headTeacherName", required = false) String headTeacherName,
                                        @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                        @RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize) {
        if ("".equals(headTeacherName)) {
            headTeacherName = null;
        } else if (headTeacherName != null){
            headTeacherName = "%" + headTeacherName + "%";
        }
        Page<Clazz> clazzPage =  clazzService.listClazzByPage(grade, clazzNum, headTeacherName, pageNo, pageSize);
        List<ClazzDTO> clazzDTO = clazzPage.stream().map(clazz -> new ClazzDTO().convertFrom(clazz)).collect(Collectors.toList());
        return new MyPage<>(clazzPage, clazzDTO);
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
     * 保存一个班级实体
     * @param clazzDTO 班级传入的参数
     */
    @PostMapping(params = "action=save_one")
    public Map<String, Integer> saveOneClazz(@RequestBody ClazzDTO clazzDTO) {
        Clazz clazz = clazzDTO.convertTo();
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("colNum",clazzService.saveClazz(clazz));
        return resultMap;
    }
}

package com.zhr.student.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhr.student.entity.Clazz;
import com.zhr.student.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping(params = "action=get_all_page")
    public Page<Clazz> listStudent(@RequestParam(value = "grade", required = false) Integer grade,
                                   @RequestParam(value = "clazzNum", required = false) Integer clazzNum,
                                   @RequestParam(value = "headTeacherId", required = false) String headTeacherId,
                                   @RequestParam(value = "type", required = false) String type,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNo,
                                   @RequestParam(value = "size", required = false, defaultValue = "2") Integer pageSize) {
        return clazzService.listClazzByPage(grade, clazzNum, headTeacherId, type, pageNo, pageSize);
    }
}

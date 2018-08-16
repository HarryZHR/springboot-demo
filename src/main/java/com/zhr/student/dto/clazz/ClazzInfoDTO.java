package com.zhr.student.dto.clazz;

import lombok.Data;

import java.util.List;

/**
 * 班级的年级和班级号的信息
 *
 * @author Harry
 */
@Data
public class ClazzInfoDTO {

    private List<Integer> clazzNums;
    private List<String> grades;

}

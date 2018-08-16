package com.zhr.student.entity;

import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * 班级对象
 *
 * @author Harry
 */
@Data
public class Clazz {

    @Getter
    public enum ClazzType {
        /**
         * 班级类型为小学
         */
        primary("小学"),

        junior("初中"),

        senior("高中");

        private String value;

        ClazzType(String value) {
            this.value = value;
        }

    }

    /**
     * 班级的id
     */
    private Long clazzId;

    /**
     * 班级所在年级
     */
    private Integer grade;

    /**
     * 班级号
     */
    private Integer clazzNum;

    /**
     * 初中还是高中
     */
    private ClazzType type;

    /**
     * 班主任
     */
    private Teacher headTeacher;

    /**
     * 是否删除
     */
    private Boolean deleteFlag;

    /**
     * 所属学校
     */
    private School school;

    /**
     * 学校里的学生
     */
    private List<Student> students;

}

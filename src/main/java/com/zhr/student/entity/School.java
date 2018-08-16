package com.zhr.student.entity;

import lombok.Data;

/**
 * 学校的对象
 *
 * @author Harry
 */
@Data
public class School {
    /**
     * 学校的id
     */
    private Long schoolId;

    /**
     * 学校的名称
     */
    private String schoolName;

}

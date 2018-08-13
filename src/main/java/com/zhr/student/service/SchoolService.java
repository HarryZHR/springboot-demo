package com.zhr.student.service;

import com.zhr.student.entity.School;

/**
 * 学校的服务层接口
 *
 * @author Harry
 */
public interface SchoolService {
    /**
     * 通过id获取学校
     *
     * @param schoolId 学校id
     * @return 学校对象
     */
    School getSchoolById(Long schoolId);
}

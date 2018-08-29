package com.zhr.student.dao;

import com.zhr.student.entity.ClazzStudent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 班级和学生关系的表
 *
 * @author Harry
 */
@Mapper
public interface ClazzStudentDAO {
    /**
     * 保存一条学生和班级的数据
     *
     * @param clazzStudent 学生
     * @return 影响行数
     */
    @Insert("INSERT INTO `clazz_student` (id, student_id, student_clazz_no, clazz_num) VALUES (#{id}, #{student.studentId}, #{studentClazzNo}, #{clazzNum})")
    Integer saveStudentClazz(ClazzStudent clazzStudent);
}

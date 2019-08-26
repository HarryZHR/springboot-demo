package com.zhr.student.dao;

import com.zhr.student.entity.ClazzStudent;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    @Insert("INSERT INTO `clazz_student` (id, student_id, student_clazz_no, clazz_num, use_flag, term_date) VALUES (#{id}, #{student.studentId}, #{studentClazzNo}, #{clazzNum}, #{useFlag}, #{termDate})")
    Integer saveStudentClazz(ClazzStudent clazzStudent);

    /**
     * 查询存在的关联关系
     *
     * @param studentId 学生id
     * @param clazzNum  班级号
     * @return 查询结果
     */
    @Select("SELECT * FROM `clazz_student` WHERE student_id = #{studentId} AND clazz_num = #{clazzNum} AND use_flag = TRUE")
    List<ClazzStudent> findAllClazzStudent(@Param(value = "studentId") Long studentId, @Param(value = "clazzNum") Integer clazzNum);

    /**
     * 删除一条对应关系
     *
     * @param id 关系id
     */
    @Update("UPDATE `clazz_student` set use_flag = FALSE WHERE id = #{id}")
    void deleteClazzStudent(Long id);

}

package com.zhr.student.repository;

import com.github.pagehelper.Page;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ClazzRepository {

    /**
     * 搜索班级，并分页
     *
     * @param grade           年级
     * @param clazzNum        班级号
     * @param headTeacherName 班主任姓名
     * @return 分页对象
     */
    @Select("<script>SELECT t1.* FROM `clazz` t1 LEFT JOIN `teacher` t2 " +
            "ON t1.head_teacher_id = t2.teacher_id " +
            "WHERE t1.delete_flag = true " +
            "<if test='grade != null'> AND t1.grade = #{grade} </if>" +
            "<if test='clazzNum != null'> AND t1.clazz_num = #{clazzNum} </if>" +
            "<if test='headTeacherName != null'> AND t2.teacher_name like #{headTeacherName} </if></script>")
    @Results({
            @Result(property = "headTeacher", column = "head_teacher_id", javaType = Teacher.class,
                    one = @One(select = "com.zhr.student.repository.TeacherRepository.getTeacherById"))
    })
    Page<Clazz> listClazzByPage(@Param(value = "grade") Integer grade,
                                @Param(value = "clazzNum") Integer clazzNum,
                                @Param(value = "headTeacherName") String headTeacherName);

    @Select("SELECT grade FROM `clazz` WHERE school_id = #{schoolId} AND delete_flag = TRUE GROUP BY grade")
    List<Integer> listGradeAll(@Param(value = "schoolId") Long schoolId);

    @Select("SELECT clazz_num FROM `clazz` WHERE school_id = #{schoolId} AND delete_flag = TRUE GROUP BY clazz_num")
    List<Integer> listClazzNumAll(@Param(value = "schoolId") Long schoolId);

    @Select("SELECT * FROM `clazz` WHERE grade = #{grade} AND clazz_num = #{clazzNum} AND school_id = #{schoolId} AND delete_flag = TRUE")
    Clazz findClazzByGradeAndClazzNum(@Param(value = "grade") Integer grade,
                                     @Param(value = "clazzNum") Integer clazzNum,
                                     @Param(value = "schoolId") Long schoolId);

    @Insert("INSERT INTO `clazz` VALUES (#{clazzId}, #{grade}, #{clazzNum}, #{headTeacher.teacherId}, #{deleteFlag}, #{school.schoolId})")
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "clazzId", before = false, resultType = java.lang.Long.class)
    Integer saveClazz(Clazz clazz);

    @Insert({"<script>" +
            " INSERT INTO `clazz` ( grade, clazz_num, head_teacher_id, delete_flag, school_id ) VALUES " +
            "<foreach item='clazz' collection='clazzList' index='index' separator =','>" +
            "(#{clazz.grade}, #{clazz.clazzNum}, #{clazz.headTeacher.teacherId}, #{clazz.deleteFlag}, #{clazz.school.schoolId}) " +
            "</foreach>" +
            "</script>"})
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "clazzId", before = false, resultType = java.lang.Long.class)
    Integer saveClazzList(@Param("clazzList") Collection<Clazz> clazzList);

    @Select("SELECT * FROM `clazz` WHERE clazz_id = #{id}")
    Clazz findClazzById(@RequestParam(value = "id") Long id);
}

package com.zhr.student.repository;

import com.github.pagehelper.Page;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.Teacher;
import org.apache.ibatis.annotations.*;

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
            "<if test='headTeacherName != null'> AND t2.teacher_name like #{headTeacherName} </if>" +
            "<if test='type != null'> AND t1.type = #{type} </if></script>")
    @Results({
            @Result(property = "headTeacher", column = "head_teacher_id", javaType = Teacher.class,
                    one = @One(select = "com.zhr.student.repository.TeacherRepository.getTeacherById"))
    })
    Page<Clazz> listClazzByPage(@Param(value = "grade") Integer grade,
                                @Param(value = "clazzNum") Integer clazzNum,
                                @Param(value = "headTeacherName") String headTeacherName);

    @Select("SELECT grade FROM `clazz` WHERE school_id = #{schoolId} GROUP BY grade")
    List<Integer> listGradeAll(@Param(value = "schoolId") Long schoolId);

    @Select("SELECT clazz_num FROM `clazz` WHERE school_id = #{schoolId} GROUP BY clazz_num")
    List<Integer> listClazzNumAll(@Param(value = "schoolId") Long schoolId);

    @Select("SELECT * FROM `clazz` WHERE grade = #{grade} AND clazz_num = #{clazzNum} AND school_id = #{schoolId}")
    Clazz getClazzByGradeAndClazzNum(@Param(value = "grade") Integer grade,
                                     @Param(value = "clazzNum") Integer clazzNum,
                                     @Param(value = "schoolId") Long schoolId);

    @Insert("INSERT INTO `clazz` VALUES (#{clazzId}, #{grade}, #{clazzNum}, #{headTeacher.teacherId}, #{deleteFlag}, #{school.schoolId})")
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "clazzId", before = false, resultType = java.lang.Long.class)
    void saveClazz(Clazz clazz);
}

package com.zhr.student.repository;

import com.github.pagehelper.Page;
import com.zhr.student.entity.Clazz;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzRepository {

    /**
     * 搜索班级，并分页
     * @param grade 年级
     * @param clazzNum 班级号
     * @param headTeacherId 班主任id
     * @param type 文理科
     * @return 分页对象
     */
    @Select("<script>SELECT * FROM `clazz` " +
            "WHERE delete_flag = true " +
            "<if test='grade != null'> AND grade = #{grade} </if>" +
            "<if test='clazzNum != null'> AND clazz_num = #{clazzNum} </if>" +
            "<if test='headTeacherId != null'> AND headTeacher_id = #{headTeacherId} </if>" +
            "<if test='type != null'> AND type = #{type} </if></script>")
    /*@Results(
            @Result(property = "headTeacher", column = "head_teacher_id")
    )*/
    Page<Clazz> listClazzByPage(@Param(value = "grade") Integer grade,
                                @Param(value = "clazzNum") Integer clazzNum,
                                @Param(value = "headTeacherId") String headTeacherId,
                                @Param(value = "type") String type);

    @Select("SELECT grade FROM `clazz` WHERE school_id = #{schoolId} GROUP BY grade")
    List<Integer> listGradeAll(@Param(value = "schoolId") String schoolId);

    @Select("SELECT clazz_num FROM `clazz` WHERE school_id = #{schoolId} GROUP BY clazz_num")
    List<Integer> listClazzNumAll(@Param(value = "schoolId") String schoolId);
}

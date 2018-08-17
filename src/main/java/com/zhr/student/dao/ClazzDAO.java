package com.zhr.student.dao;

import com.github.pagehelper.Page;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;

/**
 * 班级的dao层
 *
 * @author Harry
 */
@Mapper
public interface ClazzDAO {

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
            "ORDER BY t1.grade ASC, t1.clazz_num ASC </script>")
    @Results({
            @Result(property = "headTeacher", column = "head_teacher_id", javaType = Teacher.class,
                    one = @One(select = "com.zhr.student.dao.TeacherDAO.getTeacherById"))
    })
    Page<Clazz> listClazzByPage(@Param(value = "grade") Integer grade,
                                @Param(value = "clazzNum") Integer clazzNum,
                                @Param(value = "headTeacherName") String headTeacherName);

    /**
     * 获取当前学校中的所有年级
     *
     * @param schoolId 学校id
     * @return 所有年级的集合
     */
    @Select("SELECT grade FROM `clazz` WHERE school_id = #{schoolId} AND delete_flag = TRUE GROUP BY grade")
    List<Integer> listGradeAll(@Param(value = "schoolId") Long schoolId);

    /**
     * 获取当前学校的所有班级号
     *
     * @param schoolId 学校id
     * @return 所有班级号的集合
     */
    @Select("SELECT clazz_num FROM `clazz` WHERE school_id = #{schoolId} AND delete_flag = TRUE GROUP BY clazz_num")
    List<Integer> listClazzNumAll(@Param(value = "schoolId") Long schoolId);

    /**
     * 通过年级和班级号获取班级对象
     *
     * @param grade    年级
     * @param clazzNum 班级号
     * @param type     年级类型
     * @param schoolId 学校id
     * @return 班级
     */
    @Select("SELECT * FROM `clazz` WHERE grade = #{grade} AND clazz_num = #{clazzNum} AND type = #{type} AND school_id = #{schoolId} AND delete_flag = TRUE")
    Clazz findClazzByGradeAndClazzNum(@Param(value = "grade") Integer grade,
                                      @Param(value = "clazzNum") Integer clazzNum,
                                      @Param(value = "type") String type,
                                      @Param(value = "schoolId") Long schoolId);

    /**
     * 批量插入班级对象
     *
     * @param clazzList 多条班级记录集合
     * @return 影响行数
     */
    @Insert({"<script>" +
            " INSERT INTO `clazz` ( grade, clazz_num, type, head_teacher_id, delete_flag, school_id ) VALUES " +
            "<foreach item='clazz' collection='clazzList' index='index' separator =','>" +
            "(#{clazz.grade}, #{clazz.clazzNum}, #{clazz.type}, #{clazz.headTeacher.teacherId}, #{clazz.deleteFlag}, #{clazz.school.schoolId}) " +
            "</foreach>" +
            "</script>"})
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "clazzId", before = false, resultType = java.lang.Long.class)
    Integer saveClazzList(@Param("clazzList") Collection<Clazz> clazzList);

    /**
     * 通过id得到班级
     *
     * @param id 班级id
     * @return 班级对象
     */
    @Select("SELECT * FROM `clazz` WHERE clazz_id = #{id} AND delete_flag = TRUE")
    @Results({
            @Result(property = "headTeacher", column = "head_teacher_id", javaType = Teacher.class,
                    one = @One(select = "com.zhr.student.dao.TeacherDAO.getTeacherById"))
    })
    Clazz findClazzById(@Param(value = "id") Long id);

    /**
     * 根据班主任id获取班级
     *
     * @param headTeacherId 班主任的id
     * @return 班级
     */
    @Select("SELECT * FROM `clazz` WHERE head_teacher_id = #{headTeacherId} AND delete_flag = TRUE")
    Clazz findClazzByHeadTeacher(@Param(value = "headTeacherId") Long headTeacherId);

    /**
     * 修改班级信息
     * @param clazz 班级的信息
     * @return 影响行数
     */
    @Update("UPDATE `clazz` SET head_teacher_id = #{headTeacher.teacherId}, delete_flag = #{deleteFlag} WHERE clazz_id = #{clazzId}")
    Integer updateClazz(Clazz clazz);
}

package com.zhr.student.dao;

import com.github.pagehelper.Page;
import com.zhr.student.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 教师的dao层
 *
 * @author Harry
 */
@Mapper
public interface TeacherDAO {

    /**
     * 通过工号和密码登陆
     *
     * @param teacherNum 工号
     * @param password   密码
     * @return 教师对象
     */
    @Select("SELECT * FROM `teacher` WHERE teacher_num = #{teacherNum} AND password = #{password} AND delete_flag = TRUE")
    Teacher getTeacherByNumAndPassword(@Param(value = "teacherNum") String teacherNum, @Param(value = "password") String password);

    /**
     * 通过id获取教师对象
     *
     * @param teacherId 教师id
     * @return 教师对象
     */
    @Select("SELECT * FROM `teacher` WHERE teacher_id = #{teacherId} AND delete_flag = TRUE")
    Teacher getTeacherById(@Param(value = "teacherId") Long teacherId);

    /**
     * 查询某个学校里所有的教师
     *
     * @param schoolId 学校的id
     * @return 教师的集合
     */
    @Select("SELECT * FROM `teacher` WHERE school_id= #{schoolId} AND identity = 'teacher' AND delete_flag = TRUE")
    List<Teacher> listTeacher(@Param(value = "schoolId") Long schoolId);

    /**
     * 分页获取教师，可以通过工号和姓名进行搜索
     *
     * @param teacherNum  工号
     * @param teacherName 姓名
     * @param pageType    排序类型
     * @param schoolId    学校的id
     * @return 教师的集合
     */
    @Select("<script>SELECT t1.* from `teacher` t1 LEFT JOIN `clazz` t2 ON t2.head_teacher_id = t1.teacher_id" +
            " WHERE t1.identity = 'teacher' AND t1.school_id = #{schoolId} AND t1.delete_flag = TRUE " +
            "<if test='teacherNum != null'> AND t1.teacher_num = #{teacherNum} </if>" +
            "<if test='teacherName != null'> AND t1.teacher_name LIKE #{teacherName} </if>" +
            " ORDER BY <if test='pageType != null'> t2.head_teacher_id ASC, </if> t1.teacher_num ASC</script>")
    Page<Teacher> listTeacherByPage(@Param(value = "teacherNum") String teacherNum,
                                    @Param(value = "teacherName") String teacherName,
                                    @Param(value = "pageType") String pageType,
                                    @Param(value = "schoolId") Long schoolId);

    /**
     * 插入一条教师的记录
     *
     * @param teacher 教师的参数
     * @return 影响行数
     */
    @Insert("INSERT INTO `teacher` VALUES (#{teacherId}, #{teacherNum}, #{teacherName}, #{birthday}, #{gender}, #{identity}, #{password}, #{school.schoolId}, #{deleteFlag})")
    Integer saveTeacher(Teacher teacher);

    /**
     * 更新一条教师的记录
     *
     * @param teacher 教师的新信息
     * @return 影响行数
     */
    @Update("UPDATE `teacher` SET teacher_name = #{teacherName}, birthday = #{birthday}, gender = #{gender}, password = #{password}, delete_flag = #{deleteFlag} WHERE teacher_id = #{teacherId}")
    Integer updateTeacher(Teacher teacher);

    /**
     * 通过教师工号和学校查询教师
     * @param teacherNum 工号
     * @param schoolId 学校id
     * @return 教师
     */
    @Select("SELECT * FROM WHERE teacher_num = #{teacherNum} AND schoolId = #{schoolId} AND delete_flag")
    List<Teacher> findAllByNum(@Param(value = "teacherNum") String teacherNum,
                               @Param(value = "schoolId") Long schoolId);
}
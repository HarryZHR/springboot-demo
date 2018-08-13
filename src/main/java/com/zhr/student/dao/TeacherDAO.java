package com.zhr.student.dao;

import com.github.pagehelper.Page;
import com.zhr.student.entity.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
     * @param teacherNum 工号
     * @param password 密码
     * @return 教师对象
     */
    @Select("SELECT * FROM `teacher` WHERE teacher_num = #{teacherNum} AND password = #{password}")
    Teacher getTeacherByNumAndPassword(@Param(value = "teacherNum") String teacherNum, @Param(value = "password") String password);

    /**
     * 通过id获取教师对象
     * @param teacherId 教师id
     * @return 教师对象
     */
    @Select("SELECT * FROM `teacher` WHERE teacher_id = #{teacherId}")
    Teacher getTeacherById(@Param(value = "teacherId") Long teacherId);

    /**
     * 查询某个学校里所有的教师
     * @param schoolId 学校的id
     * @return 教师的集合
     */
    @Select("SELECT * FROM `teacher` WHERE school_id= #{schoolId} AND identity = 'teacher'")
    List<Teacher> listTeacher(@Param(value = "schoolId") Long schoolId);

    /**
     * 分页获取教师，可以通过工号和姓名进行搜索
     * @param teacherNum 工号
     * @param teacherName 姓名
     * @return 教师的集合
     */
    @Select("<script>SELECT * FROM `teacher` WHERE identity = 'teacher'" +
            "<if test='teacherNum != null'> AND teacher_num = #{teacherNum} </if>" +
            "<if test='teacherName != null'> AND teacher_name LIKE #{teacherName} </if></script>")
    Page<Teacher> listTeacherByPage(@Param(value = "teacherNum") String teacherNum,
                                    @Param(value = "teacherName") String teacherName);

    /**
     * 插入一条教师的记录
     * @param teacher 教师的参数
     * @return 影响行数
     */
    @Insert("INSERT INTO `teacher` VALUES (#{teacherId},#{teacherNum}, #{teacherName}, #{birthday}, #{gender}, #{identity}, #{password}, #{school.schoolId})")
    Integer saveTeacher(Teacher teacher);
}
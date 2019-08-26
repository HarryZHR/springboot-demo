package com.zhr.student.dao;

import com.github.pagehelper.Page;
import com.zhr.student.entity.ClazzStudent;
import com.zhr.student.entity.Student;
import org.apache.ibatis.annotations.*;

/**
 * 学生的dao层
 *
 * @author Harry
 */
@Mapper
public interface StudentDAO {

    /**
     * 获取学生分页搜索的结果
     *
     * @param studentNo   学号
     * @param studentName 姓名
     * @param schoolId    学校id
     * @param startYear   入学年份
     * @return 结果的集合
     */
    @Select("<script>SELECT * FROM `student` WHERE delete_flag = TRUE AND school_id = #{schoolId}" +
            "<if test='studentNo != null'> AND student_no = #{studentNo} </if>" +
            "<if test='studentName != null'> AND student_name LIKE #{studentName} </if>" +
            "<if test='startYear != null'> AND start_year = #{startYear} </if>" +
            " ORDER BY student_no ASC </script>")
    @Results({
            @Result(property = "clazzStudent", column = "head_teacher_id", javaType = ClazzStudent.class,
                    one = @One(select = "com.zhr.student.dao.TeacherDAO.getTeacherById"))
    })
    Page<Student> findAllStudent(@Param(value = "studentNo") String studentNo,
                                 @Param(value = "studentName") String studentName,
                                 @Param(value = "schoolId") Long schoolId,
                                 @Param(value = "startYear") Integer startYear);

    /**
     * 保存学生对象
     *
     * @param student student
     * @return 影响行数
     */
    @Insert("INSERT INTO `student` (student_id, student_no, student_name, birthday, gender, password, school_id,delete_flag, start_year) " +
            "VALUES (#{studentId}, #{studentNo}, #{studentName}, #{birthday}, #{gender}, #{password}, #{school.schoolId}, #{deleteFlag}, #{startYear}) " +
            "ON DUPLICATE KEY UPDATE " +
            "student_name = VALUES(student_name), gender = VALUES(gender), start_year = VALUES(start_year), birthday = VALUES(birthday)")
    Integer saveOrUpdateStudent(Student student);

    /*
     * 在学校里找某个学号的学生
     *
     * @param studentNo 学号
     * @param schoolId  学校
     * @return 学生
     */
    /*@Select("SELECT * FROM `student` WHERE student_no = #{studentNo} AND school_id = #{schoolId} AND delete_flag = TRUE")
    List<Student> findAllByNo(@Param(value = "studentNo") String studentNo,
                              @Param(value = "schoolId") Long schoolId);*/

    /**
     * 通过学生id获取学生对象
     *
     * @param studentId 学生id
     * @return 学生对象
     */
    @Select("SELECT * FROM `student` WHERE student_id = #{studentId} AND delete_flag = TRUE")
    Student findOneByStudentId(@Param(value = "studentId") Long studentId);

}

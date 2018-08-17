package com.zhr.student.dao;

import com.github.pagehelper.Page;
import com.zhr.student.dto.student.StudentDTO;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

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
     * @param studentNum  学号
     * @param studentName 姓名
     * @param clazzId     班级id
     * @param schoolId    学校id
     * @return 结果的集合
     */
    @Select("<script>SELECT * FROM `student` WHERE delete_flag = TRUE AND school_id = #{schoolId}" +
            "<if test='studentNum != null'> AND student_num = #{studentNum} </if>" +
            "<if test='studentName != null'> AND student_name LIKE #{studentName} </if>" +
            "<if test='clazzId != null'> AND clazz_id = #{clazzId} </if>" +
            " ORDER BY student_num ASC </script>")
    @Results({
            @Result(property = "clazz", column = "clazz_id", javaType = Clazz.class,
                    one = @One(select = "com.zhr.student.dao.ClazzDAO.findClazzById"))
    })
    Page<Student> findAllStudent(@Param(value = "studentNum") String studentNum,
                                 @Param(value = "studentName") String studentName,
                                 @Param(value = "clazzId") Long clazzId,
                                 @Param(value = "schoolId") Long schoolId);

    /**
     * @param student student
     * @return 影响行数
     */
    @Insert("INSERT INTO `student` (student_id, student_num, student_name, birthday, gender, password, clazz_id, school_id,delete_flag) " +
            "VALUES (#{studentId}, #{studentNum}, #{studentName}, #{birthday}, #{gender}, #{password}, #{clazz.clazzId}, #{school.schoolId}, #{deleteFlag})")
    Integer saveStudent(Student student);
}

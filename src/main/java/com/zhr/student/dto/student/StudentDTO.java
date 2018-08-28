package com.zhr.student.dto.student;

import com.google.common.base.Converter;
import com.zhr.student.common.util.ClazzGradeUtils;
import com.zhr.student.common.util.DateUtils;
import com.zhr.student.entity.Student;
import lombok.Data;

/**
 * 学生的数据前端交互
 *
 * @author Harry
 */
@Data
public class StudentDTO {
    private Long studentId;
    private String studentName;
    private String studentGender;
    private String studentGenderValue;
    private Integer studentAge;
    private String studentBirthday;
    private String clazzName;
    private Integer startYear;
    private String studentNo;

    public Student convertTo() {
        StudentDtoConvert dtoConvert = new StudentDtoConvert();
        return dtoConvert.convert(this);
    }

    public StudentDTO convertFrom(Student student) {
        StudentDtoConvert dtoConvert = new StudentDtoConvert();
        return dtoConvert.reverse().convert(student);
    }

    private static class StudentDtoConvert extends Converter<StudentDTO, Student>{

        @Override
        protected Student doForward(StudentDTO studentDTO) {
            Student student = new Student();
            student.setStudentName(studentDTO.getStudentName());
            student.setGender(Student.StudentGender.valueOf(studentDTO.getStudentGender()));
            student.setBirthday(DateUtils.parseDate(studentDTO.getStudentBirthday(), DateUtils.FORMAT_V2));
            student.setStartYear(studentDTO.getStartYear());
            student.setStudentNo(studentDTO.getStudentNo());
            return student;
        }

        @Override
        protected StudentDTO doBackward(Student student) {
            StudentDTO dto = new StudentDTO();
            dto.setStudentName(student.getStudentName());
            dto.setStudentGenderValue(student.getGender().getValue());
            dto.setStudentGender(student.getGender().toString());
            if (student.getBirthday() != null) {
                dto.setStudentAge(DateUtils.getAge(student.getBirthday()));
            }
            dto.setStudentNo(student.getStudentNo());
            dto.setStudentId(student.getStudentId());
            dto.setStudentBirthday(DateUtils.formatDate(student.getBirthday(), DateUtils.FORMAT_V2));
            dto.setStartYear(student.getStartYear());
            Integer year = ClazzGradeUtils.getGradeByYear(student.getStartYear());
            if (year != null) {
                dto.setClazzName("高" + ClazzGradeUtils.getGrade(year));
            }
            return dto;
        }
    }

}

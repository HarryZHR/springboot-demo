package com.zhr.student.dto.student;

import com.google.common.base.Converter;
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

    private String studentName;
    private String studentGender;
    private String studentGenderValue;
    private Integer studentAge;
    private String studentBirthday;
    private String clazzName;

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
            String clazzName = studentDTO.getClazzName();
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
            return dto;
        }
    }

}

package com.zhr.student.dto.teacher;

import com.google.common.base.Converter;
import com.zhr.student.common.util.DateUtils;
import com.zhr.student.entity.Teacher;
import lombok.Data;

/**
 * 保存教师的数据
 *
 * @author Harry
 */
@Data
public class TeacherSaveDTO {
    private Long teacherId;
    private String teacherNum;
    private String teacherName;
    private String teacherGender;
    private String teacherGenderValue;
    private String teacherBirthday;
    private Integer teacherAge;
    private String clazzName;

    public Teacher convertTo() {
        TeacherSaveDtoConvert convert = new TeacherSaveDtoConvert();
        return convert.convert(this);
    }

    public TeacherSaveDTO convertFrom(Teacher teacher) {
        TeacherSaveDtoConvert convert = new TeacherSaveDtoConvert();
        return convert.reverse().convert(teacher);
    }

    private static class TeacherSaveDtoConvert extends Converter<TeacherSaveDTO, Teacher> {

        @Override
        protected Teacher doForward(TeacherSaveDTO teacherSaveDTO) {
            Teacher teacher = new Teacher();
            teacher.setTeacherId(teacherSaveDTO.getTeacherId());
            teacher.setTeacherNum(teacherSaveDTO.teacherNum);
            teacher.setTeacherName(teacherSaveDTO.teacherName);
            teacher.setGender(Teacher.TeacherGender.valueOf(teacherSaveDTO.getTeacherGender()));
            teacher.setBirthday(DateUtils.parseDate(teacherSaveDTO.getTeacherBirthday(), DateUtils.FORMAT_V2));
            return teacher;
        }

        @Override
        protected TeacherSaveDTO doBackward(Teacher teacher) {
            TeacherSaveDTO teacherSaveDTO = new TeacherSaveDTO();
            teacherSaveDTO.setTeacherId(teacher.getTeacherId());
            teacherSaveDTO.setTeacherBirthday(DateUtils.formatDate(teacher.getBirthday(), DateUtils.FORMAT_V2));
            teacherSaveDTO.setTeacherAge(DateUtils.getAge(teacher.getBirthday()));
            teacherSaveDTO.setTeacherGender(teacher.getGender().toString());
            teacherSaveDTO.setTeacherGenderValue(teacher.getGender().getValue());
            teacherSaveDTO.setTeacherName(teacher.getTeacherName());
            teacherSaveDTO.setTeacherNum(teacher.getTeacherNum());
            return teacherSaveDTO;
        }
    }

}

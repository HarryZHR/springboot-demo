package com.zhr.student.dto.teacher;

import com.google.common.base.Converter;
import com.zhr.student.entity.Teacher;

public class TeacherSaveDTO {
    private Long teacherId;
    private String teacherNum;
    private String teacherName;
    private String teacherGender;
    private String teacherBirthday;
    private Integer teacherAge;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(String teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherGender() {
        return teacherGender;
    }

    public void setTeacherGender(String teacherGender) {
        this.teacherGender = teacherGender;
    }

    public String getTeacherBirthday() {
        return teacherBirthday;
    }

    public void setTeacherBirthday(String teacherBirthday) {
        this.teacherBirthday = teacherBirthday;
    }

    public Integer getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(Integer teacherAge) {
        this.teacherAge = teacherAge;
    }

    public Teacher convertTo() {
        TeacherSaveDTOConvert convert = new TeacherSaveDTOConvert();
        return convert.convert(this);
    }

    public TeacherSaveDTO convertFrom(Teacher teacher) {
        TeacherSaveDTOConvert convert = new TeacherSaveDTOConvert();
        return convert.reverse().convert(teacher);
    }

    private static class TeacherSaveDTOConvert extends Converter<TeacherSaveDTO, Teacher> {

        @Override
        protected Teacher doForward(TeacherSaveDTO teacherSaveDTO) {
            Teacher teacher = new Teacher();
            teacher.setTeacherId(teacherSaveDTO.getTeacherId());
            teacher.setTeacherNum(teacherSaveDTO.teacherNum);
            teacher.setTeacherName(teacherSaveDTO.teacherName);
            teacher.setGender(Teacher.TeacherGender.valueOf(teacherSaveDTO.getTeacherGender()));
//            teacher.setBirthday(DateUtils.parseDate(teacherSaveDTO.getTeacherBirthday(), DateUtils.FORMAT_V2));
            return teacher;
        }

        @Override
        protected TeacherSaveDTO doBackward(Teacher teacher) {
            TeacherSaveDTO teacherSaveDTO = new TeacherSaveDTO();
            teacherSaveDTO.setTeacherId(teacher.getTeacherId());
         /*   teacherSaveDTO.setTeacherBirthday(DateUtils.formatDate(teacher.getBirthday(), DateUtils.FORMAT_V2));
            teacherSaveDTO.setTeacherAge(DateUtils.getAge(teacher.getBirthday()));*/
            teacherSaveDTO.setTeacherGender(teacher.getGender().getValue());
            teacherSaveDTO.setTeacherName(teacher.getTeacherName());
            teacherSaveDTO.setTeacherNum(teacher.getTeacherNum());
            return teacherSaveDTO;
        }
    }
}

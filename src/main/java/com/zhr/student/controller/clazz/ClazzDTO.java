package com.zhr.student.controller.clazz;

import com.google.common.base.Converter;
import com.zhr.student.entity.Clazz;

public class ClazzDTO {

    private Integer grade;
    private Integer clazzNum;
    private String headTeacherName;
    private String type;
    private Integer studentNum;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getClazzNum() {
        return clazzNum;
    }

    public void setClazzNum(Integer clazzNum) {
        this.clazzNum = clazzNum;
    }

    public String getHeadTeacherName() {
        return headTeacherName;
    }

    public void setHeadTeacherName(String headTeacherName) {
        this.headTeacherName = headTeacherName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public Clazz convertTo(){
        ClazzDTOConvert clazzDTOConvert = new ClazzDTOConvert();
        return clazzDTOConvert.convert(this);
    }

    public ClazzDTO convertFrom(Clazz clazz){
        ClazzDTOConvert clazzDTOConvert = new ClazzDTOConvert();
        return clazzDTOConvert.reverse().convert(clazz);
    }

    private static class ClazzDTOConvert extends Converter<ClazzDTO,Clazz> {

        @Override
        protected Clazz doForward(ClazzDTO clazzDTO) {
            // dto -> entity
            return null;
        }

        @Override
        protected ClazzDTO doBackward(Clazz clazz) {
            ClazzDTO clazzDTO = new ClazzDTO();
            clazzDTO.setClazzNum(clazz.getClazzNum());
            clazzDTO.setGrade(clazz.getGrade());
            if (clazz.getType() != null) {
                clazzDTO.setType(clazz.getType().getValue());
            }
            if (clazz.getStudents() != null) {
                clazzDTO.setStudentNum(clazz.getStudents().size());
            }
            if (clazz.getHeadTeacher() != null) {
                clazzDTO.setHeadTeacherName(clazz.getHeadTeacher().getTeacherName());
            }
            return clazzDTO;
        }
    }

}

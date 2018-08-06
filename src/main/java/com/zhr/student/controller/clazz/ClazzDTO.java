package com.zhr.student.controller.clazz;

import com.google.common.base.Converter;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.School;
import com.zhr.student.entity.Teacher;

public class ClazzDTO {
    private String headTeacherId;
    private Integer grade;
    private Integer clazzNum;
    private String headTeacherName;
    private String type;
    private Integer studentNum;

    public String getHeadTeacherId() {
        return headTeacherId;
    }

    public void setHeadTeacherId(String headTeacherId) {
        this.headTeacherId = headTeacherId;
    }

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
            Clazz clazz = new Clazz();
            clazz.setGrade(clazzDTO.getGrade());
            clazz.setClazzNum(clazzDTO.getClazzNum());
            if (clazzDTO.getHeadTeacherId() != null) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(clazzDTO.getHeadTeacherId());
                clazz.setHeadTeacher(teacher);
            }
            if (clazzDTO.getType() != null) {
                if ("science".equals(clazzDTO.getType())){
                    clazz.setType(Clazz.ClazzType.science);
                } else {
                    clazz.setType(Clazz.ClazzType.Liberal_arts);
                }
            }
            return clazz;
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

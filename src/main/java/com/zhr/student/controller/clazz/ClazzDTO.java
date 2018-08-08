package com.zhr.student.controller.clazz;

import com.google.common.base.Converter;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.Teacher;
import org.apache.commons.lang3.StringUtils;

public class ClazzDTO {
    private Long clazzId;
    private String headTeacherId;
    private Integer grade;
    private Integer startClazzNum;
    private Integer endClazzNum;
    private String headTeacherName;
    private Integer studentNum;

    public Long getClazzId() {
        return clazzId;
    }

    public void setClazzId(Long clazzId) {
        this.clazzId = clazzId;
    }

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

    public Integer getStartClazzNum() {
        return startClazzNum;
    }

    public void setStartClazzNum(Integer startClazzNum) {
        this.startClazzNum = startClazzNum;
    }

    public Integer getEndClazzNum() {
        return endClazzNum;
    }

    public void setEndClazzNum(Integer endClazzNum) {
        this.endClazzNum = endClazzNum;
    }

    public String getHeadTeacherName() {
        return headTeacherName;
    }

    public void setHeadTeacherName(String headTeacherName) {
        this.headTeacherName = headTeacherName;
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
            clazz.setClazzNum(clazzDTO.getStartClazzNum());
            clazz.setGrade(clazzDTO.getGrade());
            if (StringUtils.isNotBlank(clazzDTO.getHeadTeacherId())) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(Long.valueOf(clazzDTO.getHeadTeacherId()));
                clazz.setHeadTeacher(teacher);
            }else {
                clazz.setHeadTeacher(null);
            }
            return clazz;
        }

        @Override
        protected ClazzDTO doBackward(Clazz clazz) {
            ClazzDTO clazzDTO = new ClazzDTO();
            clazzDTO.setClazzId(clazz.getClazzId());
            clazzDTO.setStartClazzNum(clazz.getClazzNum());
            clazzDTO.setGrade(clazz.getGrade());
            if (clazz.getStudents() != null) {
                clazzDTO.setStudentNum(clazz.getStudents().size());
            } else {
                clazzDTO.setStudentNum(0);
            }
            if (clazz.getHeadTeacher() != null) {
                clazzDTO.setHeadTeacherName(clazz.getHeadTeacher().getTeacherName());
            } else {
                clazzDTO.setHeadTeacherName("暂未设置");
            }
            return clazzDTO;
        }
    }

}

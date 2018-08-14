package com.zhr.student.dto.clazz;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Converter;
import com.zhr.student.common.util.ClazzGradeUtils;
import com.zhr.student.common.util.DateUtils;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.Teacher;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * 班级的返回数据
 *
 * @author Harry
 */
public class ClazzDTO {
    private Long clazzId;
    private Long headTeacherId;
    private String headTeacherName;
    private Integer grade;
    private Integer startClazzNum;
    private Integer endClazzNum;
    private Integer studentNum;
    private String type;
    private String clazzName;

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public Long getClazzId() {
        return clazzId;
    }

    public void setClazzId(Long clazzId) {
        this.clazzId = clazzId;
    }

    public Long getHeadTeacherId() {
        return headTeacherId;
    }

    public void setHeadTeacherId(Long headTeacherId) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Clazz convertTo() {
        ClazzDtoConvert clazzDTOConvert = new ClazzDtoConvert();
        return clazzDTOConvert.convert(this);
    }

    public ClazzDTO convertFrom(Clazz clazz) {
        ClazzDtoConvert clazzDTOConvert = new ClazzDtoConvert();
        return clazzDTOConvert.reverse().convert(clazz);
    }

    private static class ClazzDtoConvert extends Converter<ClazzDTO, Clazz> {

        @Override
        protected Clazz doForward(ClazzDTO clazzDTO) {
            Clazz clazz = new Clazz();
            clazz.setClazzNum(clazzDTO.getStartClazzNum());
            clazz.setGrade(clazzDTO.getGrade());
            clazz.setType(Clazz.ClazzType.valueOf(clazzDTO.getType()));
            if (clazzDTO.getHeadTeacherId() != null) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(clazzDTO.getHeadTeacherId());
                clazz.setHeadTeacher(teacher);
            } else {
                clazz.setHeadTeacher(null);
            }
            return clazz;
        }

        @Override
        protected ClazzDTO doBackward(Clazz clazz) {
            ClazzDTO clazzDTO = new ClazzDTO();
            clazzDTO.setClazzId(clazz.getClazzId());
            clazzDTO.setStartClazzNum(clazz.getClazzNum());
            clazzDTO.setType(ClazzGradeUtils.getGradeName(clazz));
            clazzDTO.setGrade(clazz.getGrade());
            clazzDTO.setClazzName(ClazzGradeUtils.getClazzName(clazz));
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

    @Override
    public String toString() {
        return "ClazzDTO{" +
                "clazzId=" + clazzId +
                ", headTeacherId=" + headTeacherId +
                ", headTeacherName='" + headTeacherName + '\'' +
                ", grade=" + grade +
                ", startClazzNum=" + startClazzNum +
                ", endClazzNum=" + endClazzNum +
                ", studentNum=" + studentNum +
                ", type='" + type + '\'' +
                '}';
    }
}

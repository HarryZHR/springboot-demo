package com.zhr.student.dto.clazz;

import com.google.common.base.Converter;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.Teacher;
import org.apache.commons.lang3.StringUtils;

public class ClazzDTO {
    private Long clazzId;
    private String headTeacherId;
    private String grade;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
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

    public Clazz convertTo() {
        ClazzDTOConvert clazzDTOConvert = new ClazzDTOConvert();
        return clazzDTOConvert.convert(this);
    }

    public ClazzDTO convertFrom(Clazz clazz) {
        ClazzDTOConvert clazzDTOConvert = new ClazzDTOConvert();
        return clazzDTOConvert.reverse().convert(clazz);
    }

    private static class ClazzDTOConvert extends Converter<ClazzDTO, Clazz> {

        @Override
        protected Clazz doForward(ClazzDTO clazzDTO) {
            Clazz clazz = new Clazz();
            clazz.setClazzNum(clazzDTO.getStartClazzNum());
            clazz.setGrade(Integer.valueOf(clazzDTO.getGrade()));
            if (StringUtils.isNotBlank(clazzDTO.getHeadTeacherId())) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(Long.valueOf(clazzDTO.getHeadTeacherId()));
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
            /*Date gradeDate = DateUtils.parseDate(clazz.getGrade().toString() + "-08-31 00:00:00", DateUtils.FORMAT_V2);
            Long gradeLong = new Date().getTime() - gradeDate.getTime();
            String gradeStr;
            if (gradeLong < 31536000000L) {
                gradeStr = "高一 ";
            } else if (gradeLong < 2 * 31536000000L) {
                gradeStr = "高二 ";
            } else {
                gradeStr = "高三 ";
            }
            clazzDTO.setGrade(gradeStr);*/
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

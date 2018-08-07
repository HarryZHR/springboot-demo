package com.zhr.student.controller.teacher;

import com.google.common.base.Converter;
import com.zhr.student.entity.Teacher;

/**
 * 新增班级时选择教师
 */
public class TeacherSelectDTO {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeacherSelectDTO convertFrom(Teacher teacher){
        TeacherSelectDTOConvert teacherSelectDTOConvert = new TeacherSelectDTOConvert();
        return teacherSelectDTOConvert.reverse().convert(teacher);
    }


    private static class TeacherSelectDTOConvert extends Converter<TeacherSelectDTO,Teacher> {

        @Override
        protected Teacher doForward(TeacherSelectDTO teacherSelectDTO) {
            return null;
        }

        @Override
        protected TeacherSelectDTO doBackward(Teacher teacher) {
            TeacherSelectDTO dto = new TeacherSelectDTO();
            dto.setId(String.valueOf(teacher.getTeacherId()));
            dto.setName(teacher.getTeacherName());
            return dto;
        }
    }
}

package com.zhr.student.dto.clazz;

import com.google.common.base.Converter;
import com.zhr.student.entity.Clazz;
import com.zhr.student.entity.Teacher;
import lombok.Data;

/**
 * @author Harry
 */
@Data
public class ClazzUpdateDTO {

    private Long clazzId;
    private Long headTeacherId;

    public Clazz convertTo() {
        ClazzUpdateDtoConvert clazzUpdateDTOConvert = new ClazzUpdateDtoConvert();
        return clazzUpdateDTOConvert.convert(this);
    }

    private static class ClazzUpdateDtoConvert extends Converter<ClazzUpdateDTO, Clazz> {

        @Override
        protected Clazz doForward(ClazzUpdateDTO clazzUpdateDTO) {
            Clazz clazz = new Clazz();
            if (clazzUpdateDTO.getHeadTeacherId() != null) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(clazzUpdateDTO.getHeadTeacherId());
                clazz.setHeadTeacher(teacher);
            } else {
                clazz.setHeadTeacher(null);
            }
            return clazz;
        }

        @Override
        protected ClazzUpdateDTO doBackward(Clazz clazz) {
            return null;
        }
    }
}

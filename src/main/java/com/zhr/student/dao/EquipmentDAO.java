package com.zhr.student.dao;

import com.zhr.student.entity.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EquipmentDAO {
    @Insert("INSERT INTO `standard_equ` (id, equ_name, equ_model, equ_factory, first, second, third) " +
            "VALUES (#{id}, #{equName}, #{equModel}, #{equFactory}, #{first}, #{second}, #{third}) ")
    Integer saveEqu(Equipment equipment);

    @Update("UPDATE equipment_information SET originId = #{originId} WHERE id = #{id}")
    Integer updateEqu1(@Param(value = "id") Long id, @Param(value = "originId") String originId);


    /**
     * 根据名称和型号获取设备
     * @param name
     * @param model
     * @return
     */
    @Select("SELECT * FROM `equipment_information` WHERE equ_name = #{name} and equ_model = #{model}")
    List<Equipment> listEqu(@Param(value = "name") String name, @Param(value = "model") String model);


    /**
     * 根据名称和型号获取设备
     * @param name
     * @param model
     * @return
     */
    @Select("SELECT * FROM `equipment_information` WHERE equ_name = #{name} and equ_model = #{model} and equ_factory = #{factory}")
    List<Equipment> listEquFactory(@Param(value = "name") String name, @Param(value = "model") String model, @Param(value = "factory") String factory);

    @Update("UPDATE `equipment_information` SET is_show = #{flag} WHERE id = #{id}")
    void updateEqu(@Param(value = "id") Long id, @Param(value = "flag") Boolean flag);

    @Select("SELECT * FROM `equipment_information` ")
    List<Equipment> listAll();

    @Select("SELECT * FROM `equipment_information` WHERE equ_name = #{name} and equ_model = #{model} and name = #{category}")
    List<Equipment> listEquByNameModelAndCate(@Param(value = "name") String name,@Param(value = "model") String model,@Param(value = "category") String category);
}

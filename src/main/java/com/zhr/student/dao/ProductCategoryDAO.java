package com.zhr.student.dao;

import com.zhr.student.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductCategoryDAO {

    @Select("SELECT * FROM productcategory WHERE name = #{name} AND grade = #{grade} AND is_show = true")
    List<ProductCategory> getProductByName(@Param(value = "name") String name, @Param(value = "grade") Integer grade);
}

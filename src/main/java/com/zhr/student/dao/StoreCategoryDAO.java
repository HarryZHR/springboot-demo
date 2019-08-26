package com.zhr.student.dao;

import com.zhr.student.entity.StoreProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StoreCategoryDAO {

    @Select("SELECT * FROM storeproductcategory WHERE storeId = #{storeId} and productCategoryId = #{productCategoryId}")
    StoreProductCategory findOne(@Param(value = "storeId") Long storeId, @Param(value = "productCategoryId") Long productCategoryId);
}

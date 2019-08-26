package com.zhr.student.dao;

import com.zhr.student.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductDAO {

    @Select("SELECT * FROM `supplier`")
    List<Product> findAll();
}

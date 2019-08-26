package com.zhr.student.service;

import com.zhr.student.dao.ProductCategoryDAO;
import com.zhr.student.entity.ProductCategory;
import com.zhr.student.service.itf.ProductCategoryService;

import javax.annotation.Resource;
import java.util.List;

public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Resource
    private ProductCategoryDAO productCategoryDAO;

    @Override
    public ProductCategory getProductCategoryByThree(String first, String second, String third) {
        List<ProductCategory> productCategories1 = productCategoryDAO.getProductByName(first, 0);
        List<ProductCategory> productCategories2 = productCategoryDAO.getProductByName(second, 1);
        List<ProductCategory> productCategories3 = productCategoryDAO.getProductByName(third, 2);

        return null;
    }
}

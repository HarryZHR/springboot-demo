package com.zhr.student.service.itf;

import com.zhr.student.entity.ProductCategory;

public interface ProductCategoryService {
    ProductCategory getProductCategoryByThree(String first, String second, String third);
}

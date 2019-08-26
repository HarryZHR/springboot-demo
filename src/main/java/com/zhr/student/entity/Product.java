package com.zhr.student.entity;

public class Product {
    private String id;
    private String name;
    private String categoryId;
    private String originStoreId;
    private String supplierName;
    private String storeId;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getOriginStoreId() {
        return originStoreId;
    }

    public void setOriginStoreId(String originStoreId) {
        this.originStoreId = originStoreId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}

package com.zhr.student.controller;

import com.zhr.student.common.util.ExcelUtil;
import com.zhr.student.dao.StoreCategoryDAO;
import com.zhr.student.entity.Application;
import com.zhr.student.entity.Product;
import com.zhr.student.entity.StoreProductCategory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("product")
@Controller("productController")
public class ProductController {

    @Resource
    private StoreCategoryDAO storeCategoryDAO;

    @GetMapping("a")
    public String a() throws IOException {
        List<Product> products = readProductExcel();
        List<String> sqls1 = new ArrayList<>();
        List<String> sqls2 = new ArrayList<>();
        List<Application> applications = new ArrayList<>();
        for (Product product : products) {
            String sql1 = "UPDATE product SET store_id = " + product.getStoreId() + " WHERE id = "+ product.getId() +";";
            sqls1.add(sql1);
//
            Application application = new Application();
            application.setCategoryId(product.getCategoryId());
            application.setStoreId(product.getStoreId());
            if (!applications.contains(application)) {
                applications.add(application);
            }
        }


        for (Application application : applications) {
            StoreProductCategory storeProductCategory = storeCategoryDAO.findOne(Long.valueOf(application.getStoreId()), Long.valueOf(application.getCategoryId()));
            if (storeProductCategory == null) {
                String sql2 = "INSERT INTO store_productcategory (stores_id, productCategories_id) VALUES (" + application.getStoreId() + ","+  application.getCategoryId() +") ;";
                sqls2.add(sql2);
            }
        }

//
//
        write(sqls1);
        write2(sqls2);
        return "";
    }

    private List<Product> readProductExcel() throws IOException {
        File file =  new File("D://采购池中的商品匹配.xlsx");
        List<Product> products = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(file);
        Sheet sheet = ExcelUtil.getFirstSheet(inputStream);
        inputStream.close();
        if (sheet != null) {
            int i = 1;
            Row row;
            while (i <= 14000) {
                row = sheet.getRow(i);
                if (row != null) {
                    Product product = new Product();
                    int columnCount = row.getLastCellNum();
                    int j = 0;
                    while (j < columnCount) {
                        Cell cell = row.getCell(j);
                        String content = ExcelUtil.getCellValue(cell);
                        switch (j++) {
                            case 0:
                                product.setId(content);
                                break;
                            case 1:
                                product.setName(content);
                                break;
                            case 2:
                                product.setCategoryId(content);
                                break;
                            case 3:
                                product.setOriginStoreId(content);
                                break;
                            case 4:
                                product.setSupplierName(content);
                                break;
                            case 5:
                                product.setStoreId(content);
                                break;
                            default:
                                break;
                        }
                    }
                    products.add(product);
                }
                i++;
            }
        }
        return products;
    }

    private void write(List<String> sql1) {
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();
        List<String> titles = new ArrayList<>();
        titles.add("sql1");

        Row row1 = sh.createRow(0);
        for(int i = 0; i < titles.size(); i++){
            Cell cell = row1.createCell(i);
            cell.setCellValue(titles.get(i));
        }

        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper creationHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        for(int i = 0; i < sql1.size(); i++){
            Row row = sh.createRow(i);
            Cell cell0 = row.createCell(0);
            cell0.setCellType(CellType.STRING);
            cell0.setCellValue(sql1.get(i));

        }
        FileOutputStream out;
        try {
            out = new FileOutputStream("D://sql1.xlsx");
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }
    private void write2(List<String> sql) {
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();
        List<String> titles = new ArrayList<>();
        titles.add("sql2");

        Row row1 = sh.createRow(0);
        for(int i = 0; i < titles.size(); i++){
            Cell cell = row1.createCell(i);
            cell.setCellValue(titles.get(i));
        }

        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper creationHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        for(int i = 0; i < sql.size(); i++){
            Row row = sh.createRow(i);
            Cell cell0 = row.createCell(0);
            cell0.setCellType(CellType.STRING);
            cell0.setCellValue(sql.get(i));
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream("D://sql2.xlsx");
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }
}

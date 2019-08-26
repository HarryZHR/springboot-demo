package com.zhr.student.controller;

import com.zhr.student.common.util.ExcelUtil;
import com.zhr.student.dao.StoreDAO;
import com.zhr.student.entity.Product;
import com.zhr.student.entity.Store;
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


@Controller("supplierController")
@RequestMapping("/supplier")
public class SupplierController {

    @Resource
    private StoreDAO storeDAO;


    @GetMapping("compare")
    public String compareName() throws IOException {
        List<Product> products = readProductExcel();
        for (Product product : products) {
            if (product.getSupplierName() != null) {
                Store store = storeDAO.findByName(product.getSupplierName().trim());
                if (store != null) {
                    product.setStoreId(String.valueOf(store.getId()));
                }
            }
        }
        write(products);
        return "1";
    }

    private List<Product> readProductExcel() throws IOException {
        File file =  new File("C:\\Users\\user\\Desktop\\采购池中的商品.xlsx");
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

    private void write(List<Product> products) {
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();
        List<String> titles = new ArrayList<>();
        titles.add("id");
        titles.add("名称");
        titles.add("商品分类id");
        titles.add("采购池id");
        titles.add("供应商名称");
        titles.add("店铺id");

        Row row1 = sh.createRow(0);
        for(int i = 0; i < titles.size(); i++){
            Cell cell = row1.createCell(i);
            cell.setCellValue(titles.get(i));
        }

        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper creationHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        for(int i = 1; i < products.size(); i++){
            Row row = sh.createRow(i);
            Cell cell0 = row.createCell(0);
            cell0.setCellType(CellType.STRING);
            cell0.setCellValue(products.get(i).getId());
            Cell cell1 = row.createCell(1);
            cell1.setCellType(CellType.STRING);
            cell1.setCellValue(products.get(i).getName());
            Cell cell2 = row.createCell(2);
            cell2.setCellType(CellType.STRING);
            cell2.setCellValue(products.get(i).getCategoryId());
            Cell cell3 = row.createCell(3);
            cell3.setCellType(CellType.STRING);
            cell3.setCellValue(products.get(i).getOriginStoreId());
            Cell cell4 = row.createCell(4);
            cell4.setCellType(CellType.STRING);
            cell4.setCellValue(products.get(i).getSupplierName());
            Cell cell5 = row.createCell(5);
            cell5.setCellType(CellType.STRING);
            cell5.setCellValue(products.get(i).getStoreId());
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream("D://采购池中的商品匹配店铺.xlsx");
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }

}




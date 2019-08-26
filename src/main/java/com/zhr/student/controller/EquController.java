package com.zhr.student.controller;

import com.zhr.student.common.util.ExcelUtil;
import com.zhr.student.dao.EquipmentDAO;
import com.zhr.student.dao.ProductCategoryDAO;
import com.zhr.student.entity.Equipment;
import com.zhr.student.entity.ProductCategory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller("equController")
@RequestMapping("/equ")
public class EquController {

    @Resource
    private EquipmentDAO equipmentDAO;

    @Resource
    private ProductCategoryDAO productCategoryDAO;

    @GetMapping("/export")
    @ResponseBody
    public int exportEquipment() {
        List<Equipment> equipments = equipmentDAO.listAll();
        List<String> sqls = new ArrayList<>();
        for (Equipment equipment : equipments) {
            String sql = "update equipment_information set origin_id= "+equipment.getOriginId()+" where id = "+equipment.getId()+";";
            sqls.add(sql);
        }
        write2(sqls);
        return 0;
    }

    @GetMapping("/import")
    @ResponseBody
    public int importCategory() throws IOException {
//        List<Equipment> equipmentHas = equipmentDAO.listAll();

        List<Equipment> oneToMany = new ArrayList<>();
        List<Equipment> none = new ArrayList<>();
        List<Equipment> equipments = read();
        int count = 0;
        for (Equipment equipment : equipments) {
            String name = equipment.getEquName() != null ? equipment.getEquName().trim() : null;
            String model = equipment.getEquModel() != null ? equipment.getEquModel().trim() : null;
            String cate = equipment.getCategory() != null ? equipment.getCategory().trim() : null;
            List<Equipment> equList = equipmentDAO.listEquByNameModelAndCate(name, model, cate);
            if (equList.size() > 1) {
                oneToMany.add(equipment);
                System.out.println(111);
//                List<Equipment> equipmentList = equipmentDAO.listEquFactory(equipment.getEquName(), equipment.getEquModel(), equipment.getEquFactory());
//                if (equipmentList.size() == 1) {
//                    equipment.setId(equipmentList.get(0).getId());
//                } else {
//                }
            } else if (equList.size() == 1){
//                List<ProductCategory> productCategories = productCategoryDAO.getProductByName(equipment.getCategory(), 2);
//                if (productCategories.size() > 1) {
//                    System.out.println(2222);
//                } else if (productCategories.size() == 1) {
//                    ProductCategory productCategory = productCategories.get(0);
                    Equipment equipment1 = equList.get(0);
//                    equipment1.setCategory(String.valueOf(productCategory.getId()));
                    equipmentDAO.updateEqu1(equipment1.getId(), equipment.getOriginId());
//                }
            } else {
                none.add(equipment);
            }


        }
        write(oneToMany, "oneToMany");
        write(none, "none");
        return count;
    }

    private List<Equipment> read() throws IOException {
        File file =  new File("C://Users//user//Desktop//公共库0524.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        Sheet sheet = ExcelUtil.getFirstSheet(inputStream);
        inputStream.close();
        List<Equipment> equs = new ArrayList<>();
        if (sheet != null) {
            int i = 1;
            Row row;
            while (i <= 30000) {
                row = sheet.getRow(i);
                if (row != null) {
                    Equipment cate = new Equipment();
                    int columnCount = row.getLastCellNum();
                    int j = 0;
                    while (j < columnCount) {
                        Cell cell = row.getCell(j);
                        String content = ExcelUtil.getCellValue(cell);
                        switch (j++) {
                            case 0:
                                cate.setOriginId(content);
                                break;
                            case 2:
                                cate.setEquName(content);
                                break;
                            case 3:
                                cate.setEquModel(content);
                                break;
                            case 7:
                                cate.setCategory(content);
                                break;
                            case 11:
                                cate.setEquFactory(content);
                                break;
                            default:
                                break;
                        }
                    }
                    equs.add(cate);
                }
                i++;
            }
        }
        return equs;
    }

    private void write(List<Equipment> equipmentList, String type) {
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();
        /*List<String> titles = new ArrayList<>();
        titles.add("id");
        titles.add("equ_name");
        titles.add("equ_model");
        titles.add("equ_factory");
        titles.add("category");
        titles.add("originId");
        Row row1 = sh.createRow(0);
        for(int i = 0; i < titles.size(); i++){
            Cell cell = row1.createCell(i);
            cell.setCellValue(titles.get(i));
        }*/

        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper creationHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        for(int i = 0; i < equipmentList.size(); i++){
            Row row = sh.createRow(i);

            Cell cell2 = row.createCell(1);
            cell2.setCellType(CellType.STRING);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(equipmentList.get(i).getEquName());
            Cell cell3 = row.createCell(2);
            cell3.setCellType(CellType.STRING);
            cell3.setCellValue(equipmentList.get(i).getEquModel());
            Cell cell4 = row.createCell(3);
            cell4.setCellType(CellType.STRING);
            cell4.setCellValue(equipmentList.get(i).getEquFactory());
            Cell cell5 = row.createCell(4);
            cell5.setCellType(CellType.NUMERIC);
            cell5.setCellValue(equipmentList.get(i).getCategory());
            Cell cell6 = row.createCell(5);
            cell6.setCellType(CellType.NUMERIC);
            cell6.setCellValue(equipmentList.get(i).getOriginId());

        }
        FileOutputStream out;
        try {
            if ("oneToMany".equals(type)) {
                out = new FileOutputStream("D://对应数据库多条数据.xlsx");
            } else {
                out = new FileOutputStream("D://对应数据库0条数据.xlsx");
            }
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }

    private void write2(List<String> sqls) {
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();

        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper creationHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        for(int i = 0; i < sqls.size(); i++){
            Row row = sh.createRow(i);

            Cell cell2 = row.createCell(1);
            cell2.setCellType(CellType.STRING);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(sqls.get(i));

        }
        FileOutputStream out;
        try {
            out = new FileOutputStream("D://sqll.xlsx");
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }

}

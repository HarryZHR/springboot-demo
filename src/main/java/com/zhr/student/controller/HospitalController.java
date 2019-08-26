package com.zhr.student.controller;

import com.zhr.student.common.util.ExcelUtil;
import com.zhr.student.dao.HospitalDAO;
import com.zhr.student.entity.Admin;
import com.zhr.student.entity.Hospital;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("hospital")
public class HospitalController {

    @Resource
    private HospitalDAO hospitalDAO;

//    @PostMapping("insert")
    @RequestMapping(value = "", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String a() throws IOException {
        List<Admin> admins = readProductExcel();
        List<String> sqls = new ArrayList<>();
        List<String> noHospital = new ArrayList<>();
        for (Admin admin : admins) {
            String[] hospitals = admin.getHospitalId().split("/");
            if (hospitals.length > 1) {
                for (String hospital : hospitals) {
                    Hospital hospitalId = hospitalDAO.findOne(hospital);
                    if (hospitalId != null) {
                        String sql = "insert into hospital_admin (hospitals_id, admins_id) values (" + hospitalId.getId() + "," + admin.getAdminName() + ");";
                        sqls.add(sql);
                    } else {
                        noHospital.add(hospital);
                    }
                }
            } else {
                Hospital hospitalId = hospitalDAO.findOne(admin.getHospitalId());
                if (hospitalId != null) {
                    String sql = "insert into hospital_admin (hospitals_id, admins_id) values (" + hospitalId.getId() + "," + admin.getAdminName() + ");";
                    sqls.add(sql);
                } else {
                    noHospital.add(admin.getHospitalId());
                }
            }
        }
        write(sqls);
        write2(noHospital);
        return "";
    }

    private List<Admin> readProductExcel() throws IOException {
        File file =  new File("C:\\Users\\user\\Desktop\\区域经理负责医院明细2019.05.31.xlsx");
        List<Admin> admins = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(file);
        Sheet sheet = ExcelUtil.getFirstSheet(inputStream);
        inputStream.close();
        if (sheet != null) {
            int i = 1;
            Row row;
            while (i <= 200) {
                row = sheet.getRow(i);
                if (row != null) {
                    Admin admin = new Admin();
                    int columnCount = row.getLastCellNum();
                    int j = 0;
                    while (j < columnCount) {
                        Cell cell = row.getCell(j);
                        String content = ExcelUtil.getCellValue(cell);
                        switch (j++) {
                            case 0:
                                break;
                            case 2:
                                admin.setAdminName(content);
                                break;
                            case 3:
                                admin.setHospitalId(content);
                                break;
                            default:
                                break;
                        }
                    }
                    admins.add(admin);
                }
                i++;
            }
        }
        return admins;
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
            out = new FileOutputStream("D://insert.xlsx");
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }

    private void write2(List<String> sql1) {
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
            out = new FileOutputStream("D://insert2.xlsx");
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }

}

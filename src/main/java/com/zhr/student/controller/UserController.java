package com.zhr.student.controller;

import com.zhr.student.common.util.ExcelUtil;
import com.zhr.student.common.util.MD5EncryptUtils;
import com.zhr.student.dao.EquipmentDAO;
import com.zhr.student.dao.ProductCategoryDAO;
import com.zhr.student.entity.Equipment;
import com.zhr.student.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
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

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    public static final String ENCRYPT_FACTOR = "Quote";

    @GetMapping("/import")
    @ResponseBody
    public int importCategory() throws IOException {
        List<User> users = read();
        int count = 0;
        for (User user : users) {
            if (StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getMobile()) && user.getUsername().length() > 4 && user.getMobile().length() > 4) {
                String password = user.getUsername().substring(0, 4) + user.getMobile().substring(0, 4);
                user.setPassword(password);
                user.setSecret(DigestUtils.md5DigestAsHex((ENCRYPT_FACTOR + "keduAdmin888").getBytes("UTF-8")));
            } else {
                user.setPassword("123456");
                user.setSecret(DigestUtils.md5DigestAsHex((ENCRYPT_FACTOR + 123456).getBytes("UTF-8")));
            }
        }
        write(users);
        return count;
    }

    private List<User> read() throws IOException {
        File file =  new File("C://Users//user//Desktop//111.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        Sheet sheet = ExcelUtil.getFirstSheet(inputStream);
        inputStream.close();
        List<User> users = new ArrayList<>();
        if (sheet != null) {
            int i = 1;
            Row row;
            while (i <= 300) {
                row = sheet.getRow(i);
                if (row != null) {
                    User user = new User();
                    int columnCount = row.getLastCellNum();
                    int j = 0;
                    while (j < columnCount) {
                        Cell cell = row.getCell(j);
                        String content = ExcelUtil.getCellValue(cell);
                        switch (j++) {
                            case 0:
                                user.setUsername(content);
                                break;
                            case 1:
                                user.setName(content);
                                break;
                            case 2:
                                user.setCompany(content);
                                break;
                            case 3:
                                user.setPosition(content);
                                break;
                            case 4:
                                user.setMobile(content);
                                break;
                            case 5:
                                user.setEmail(content);
                                break;
                            case 6:
                                user.setDepartment(content);
                                break;
                            default:
                                break;
                        }
                    }
                    users.add(user);
                }
                i++;
            }
        }
        return users;
    }

    private void write(List<User> users) {
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();

        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper creationHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        for(int i = 0; i < users.size(); i++){
            Row row = sh.createRow(i);
            Cell cell1 = row.createCell(0);
            cell1.setCellType(CellType.STRING);
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue(users.get(i).getName());

            Cell cell2 = row.createCell(1);
            cell2.setCellType(CellType.STRING);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(users.get(i).getUsername());

            Cell cell3 = row.createCell(2);
            cell3.setCellType(CellType.STRING);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(users.get(i).getMobile());

            Cell cell4 = row.createCell(3);
            cell4.setCellType(CellType.STRING);
            cell4.setCellValue(users.get(i).getPassword());

            Cell cell5 = row.createCell(4);
            cell5.setCellType(CellType.NUMERIC);
            cell5.setCellValue(users.get(i).getSecret());

        }
        FileOutputStream out;
        try {
            out = new FileOutputStream("D://aaasssaaa.xlsx");
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }


}

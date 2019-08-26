package com.zhr.student.common.util;

import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * Util - Excel导入
 *
 * @author Harry.zhang on 2018/12/18
 */
public class ExcelUtil {

    public static Sheet getFirstSheet(InputStream inputStream) {
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            return workbook.getSheetAt(0);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        CellType type = cell.getCellTypeEnum();
        String res = "";
        switch (type) {
            case STRING:
                res = cell.getStringCellValue().trim();
                break;
            case NUMERIC:
                //百分数
                cell.setCellType(CellType.NUMERIC);
                res = String.valueOf(Math.round(cell.getNumericCellValue()));
                break;
            // boolean
            case BOOLEAN:
                res = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            default:
                break;
        }
        return res;
    }

    public static int getRowNumber(InputStream inputStream) throws IOException {
        Sheet sheet = getFirstSheet(inputStream);
        inputStream.close();
        if (sheet != null) {
            return sheet.getLastRowNum();
        }
        return 0;
    }
}

package main.java.hrAdmin.importAttendance;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel {

    public  String ReadExcelColumn(String sheetName, int rNum, int cNum, String filePath) {
    	
    	String data = "";
    	
        try {
            FileInputStream fis = new FileInputStream(filePath);
            
            Workbook wb = WorkbookFactory.create(fis);
            
            Sheet s = wb.getSheet(sheetName);
            Row r = s.getRow(rNum);
            Cell c = r.getCell(cNum);
            
            if (c.getCellType() == CellType.STRING) {
                data = c.getStringCellValue();
                // Process the string value
            } else if (c.getCellType() == CellType.NUMERIC) {
                double numericValue = c.getNumericCellValue();
                
                data = String.valueOf(numericValue);
            } else {
            }
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public int countColumns(String sheetName) {
        int columnCount = 0;
        try {
            FileInputStream fis = new FileInputStream("D:\\workerInfor.xlsx");
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);
            
            if (sheet != null) {
                Row firstRow = sheet.getRow(0);
                if (firstRow != null) {
                    columnCount = firstRow.getLastCellNum();
                }
            }

            wb.close(); // Đảm bảo đóng Workbook sau khi sử dụng
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnCount;
    }
    
    public int countRows(String sheetName, String filePath) {
        int rowCount = 0;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);
            
            if (sheet != null) {
                rowCount = sheet.getLastRowNum() + 1;
            }

            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }
}

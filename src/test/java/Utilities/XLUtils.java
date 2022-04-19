package Utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtils {
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;

    public static int getRowCount(String xlFile, String xlSheet) throws IOException {
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlSheet);
        int rowCount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }

    public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException{
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlSheet);
        row = ws.getRow(rowNum);
        int columnCount = row.getLastCellNum();
        wb.close();
        fi.close();
        return columnCount;
    }

    public static String getCellData(String xlFile, String xlSheet, int rowNum, int columnNum) throws IOException{
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlSheet);
        row = ws.getRow(rowNum);
        cell = row.getCell(columnNum);
        String data;
        try {
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        wb.close();
        fi.close();
        return data;
    }

    public static void setCellData(String xlFile, String xlSheet, int rowNum, int columnNum, Object data,CellType ctype) throws IOException {
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlSheet);
        row = ws.getRow(rowNum);
        if(row==null)
        	row=ws.createRow(rowNum);
        cell=row.getCell(columnNum);
        if (cell==null)
        	cell = row.createCell(columnNum);

       	if (data instanceof Long )
       		cell.setCellValue((Long)data);
       	if (data instanceof Integer )
       		cell.setCellValue((Integer)data);
       	if (data instanceof String )
        	cell.setCellValue((String)data);
        if(data instanceof Boolean)
            cell.setCellValue((Boolean)data);
        cell.setCellType(ctype);
        fo = new FileOutputStream(xlFile);
        wb.write(fo);
        wb.close();
        fo.close();
        fi.close();
    }
}
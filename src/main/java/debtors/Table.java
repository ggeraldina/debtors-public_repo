package debtors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.HashMap;

public class Table {
    private static final Logger LOG = LoggerFactory.getLogger(Table.class);
    private ConfigUrl configUrl = new ConfigUrl();
    private ConfigTable config = new ConfigTable();
    private final int startRowData = 6;
    private HSSFWorkbook book;
    private HSSFSheet sheet;
    private Map<String, HSSFCellStyle> styles = new HashMap<String, HSSFCellStyle>();
    
    public Table() {
        book = new HSSFWorkbook();
        createStyle();
    }

    private void createStyle() {
        styles.put("Date", StyleTable.createStyleDate(book));
        styles.put("Title", StyleTable.createStyleTitle(book));
        styles.put("Subtitle", StyleTable.createStyleSubtitle(book));
        styles.put("HeaderTable", StyleTable.createStyleHeaderTable(book));
        styles.put("DataRightTable",StyleTable.createStyleDataRightTable(book));
        styles.put("TotalSum", StyleTable.createStyleTotalSum(book));
        styles.put("Uderline", StyleTable.createStyleUderline(book));
    }    
    
    public void createRecord(Data data) {
        
        sheet = book.createSheet(((Integer) data.getClientId()).toString());
        
        createHeader(book, sheet, data);
        createRowsData(book, sheet, data);
        createRowTotalSum(book, sheet, data);
        createRowSignature(book, sheet, data);
        
        for (int i = 0; i < 5; i++) {
            sheet.setColumnWidth(i, 4500);
        }
        
        saveToFile(book, data);
    }

    private void createHeader(HSSFWorkbook book, HSSFSheet sheet, Data data) {
        createRowDateToday(book, sheet);
        createRowTitle(book, sheet);
        createRowClientId(book, sheet, data.getClientId());
        createRowAddress(book, sheet, data.getClientAdr());
        createRowHeadersTable(book, sheet);
    }   

    private void createRowDateToday(HSSFWorkbook book, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(4);        
        cell.setCellStyle(styles.get("Date"));
        cell.setCellValue(new Date());
    }

    private void createRowTitle(HSSFWorkbook book, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(1);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(styles.get("Title"));
        cell.setCellValue(config.TITLE);
        cell.getRow().setHeight((short) 700);
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,4)); 
    }

    private void createRowHeadersTable(HSSFWorkbook book, HSSFSheet sheet) {
        String arrRow5[] = { config.COLUMN_0, 
                             config.COLUMN_1,
                             config.COLUMN_2,
                             config.COLUMN_3,
                             config.COLUMN_4 };
        HSSFRow row = sheet.createRow(5);
        for (int i = 0; i < 5; i++) {
            HSSFCell cell = row.createCell(i); 
            cell.setCellStyle(styles.get("HeaderTable"));      
            cell.setCellValue(arrRow5[i]);
        }
    }

    private void createRowClientId(HSSFWorkbook book, HSSFSheet sheet, Integer clientId) {
        HSSFRow row = sheet.createRow(2);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(styles.get("Subtitle"));
        try {
            // TODO string subtitle
            String str = "Лиц.счет " + clientId.toString();
            cell.setCellValue(new String(str.getBytes(), "utf-8"));
            cell.getRow().setHeight((short) 500);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage());
        }
        sheet.addMergedRegion(new CellRangeAddress(2,2,0,4)); 
    }

    private void createRowAddress(HSSFWorkbook book, HSSFSheet sheet, String address) {
        HSSFRow row = sheet.createRow(3);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(styles.get("Subtitle"));
        cell.setCellValue(address);
        sheet.addMergedRegion(new CellRangeAddress(3,3,0,4)); 
    }

    private void createRowsData(HSSFWorkbook book, HSSFSheet sheet, Data data){
        Integer clientId = data.getClientId();
        for (int i = 0; i < data.getDept().size(); i++) {
            HSSFRow row = sheet.createRow(startRowData + i);
            // Should specify Locale.RU
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM.yyyy");        
            String date = dateFormat.format(data.getDept().get(i).getMonth());
            Double dept = data.getDept().get(i).getSum();
            // TODO string data table
            String arrRow5[] = {clientId.toString(), "Наем", "Наем жилья", date};
            for(int j = 0; j < 4; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(styles.get("DataRightTable"));      
                try {
                    cell.setCellValue(new String(arrRow5[j].getBytes(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    LOG.error(e.getMessage());
                }
            }
            HSSFCell cellSum = row.createCell(4);
            cellSum.setCellStyle(styles.get("DataRightTable"));
            cellSum.setCellValue(dept);
        }
    }

    private void createRowTotalSum(HSSFWorkbook book, HSSFSheet sheet, Data data) {
        int countRowData = data.getDept().size();
        int numberRow = startRowData + countRowData;
        HSSFRow row = sheet.createRow(numberRow);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(styles.get("TotalSum"));
        cell.setCellValue(config.TOTAL_SUM);
        for (int i = 1; i < 4; i++) {
            HSSFCell cellEmpty = row.createCell(i);
            cellEmpty.setCellStyle(styles.get("TotalSum"));
        }
        sheet.addMergedRegion(new CellRangeAddress(numberRow,numberRow,0,3)); 
        HSSFCell cellTotalSum = row.createCell(4);
        cellTotalSum.setCellStyle(styles.get("TotalSum"));
        cellTotalSum.setCellValue(data.getTotalSum());
    }

    private void createRowSignature(HSSFWorkbook book, HSSFSheet sheet, Data data) {
        int countRowData = data.getDept().size();
        int numberRow = startRowData + countRowData + 2;
        HSSFRow row = sheet.createRow(numberRow);
        HSSFCell cellSignature = row.createCell(0);
        cellSignature.setCellValue(config.SIGNATURE_JOB);
        sheet.addMergedRegion(new CellRangeAddress(numberRow, numberRow, 0, 2));
        HSSFCell cellUnderline = row.createCell(3);
        cellUnderline.setCellStyle(styles.get("Uderline"));
        HSSFCell cellSignatureName = row.createCell(4);
        cellSignatureName.setCellValue(config.SIGNATURE_NAME);
    }

    private void saveToFile(HSSFWorkbook book, Data data){
        // Записываем всё в файл
        // TODO string url
        File filePath = new File(configUrl.OUT);
        filePath.mkdir();
        String nameFile = data.getClientAdr();
        nameFile = nameFile.replaceAll("/", " # ");
        nameFile = nameFile +  "_" + data.getClientId();
        SimpleDateFormat dateF = new SimpleDateFormat ("yyyy-MM-dd");
        File filePath2 = new File(filePath + "\\" + dateF.format(new Date()));
        filePath2.mkdir();
        File file = new File(filePath2 + "\\" + nameFile + ".xls");    
        try (FileOutputStream outFile = new FileOutputStream(file)) {
            book.write(outFile);
        } catch (IOException e) {
            LOG.error("error I/O " + data.getClientAdr() + "\n" + e.getMessage());
        } finally {
            try { 
                book.close();
            } catch (IOException e) {
                LOG.error("error 2 I/O " + data.getClientAdr() + "\n" + e.getMessage());
            } 
        }
    }
}
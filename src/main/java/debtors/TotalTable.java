package debtors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.HashMap;

public class TotalTable {
    private static final Logger LOG = LoggerFactory.getLogger(TotalTable.class);
    private ConfigUrl configUrl = new ConfigUrl();
    private ConfigTotalTable config = new ConfigTotalTable();
    private final int startRowData = 2;        
    private HSSFWorkbook book;
    private HSSFSheet sheet;
    private int countRecord;
    private double totalSum;
    private Map<String, HSSFCellStyle> styles = new HashMap<String, HSSFCellStyle>();

    public TotalTable() {
        book = new HSSFWorkbook();
        sheet = book.createSheet("Total list");
        countRecord = 0;
        totalSum = 0;
        createStyle();
        createRowTitle();
        createRowHeadersTable();
    }

    private void createStyle() {
        styles.put("Title", StyleTable.createStyleTitle(book));
        styles.put("HeaderTotalTable", StyleTable.createStyleHeaderTotalTable(book));
        styles.put("DataLeftTable",StyleTable.createStyleDataLeftTable(book));
        styles.put("DataCenterTable",StyleTable.createStyleDataCenterTable(book));
        styles.put("DataRightTable",StyleTable.createStyleDataRightTable(book));
        styles.put("TotalSum", StyleTable.createStyleTotalSum(book));
        styles.put("Uderline", StyleTable.createStyleUderline(book));
    }

    public void saveToFile(){
        createRowTotalSum();
        createRowSignature();
        createRowSignatureDate();     
        sheet.autoSizeColumn(0);
        // sheet.autoSizeColumn(1);
        sheet.setColumnWidth(1, 6500);   
        sheet.autoSizeColumn(2); 
        // sheet.autoSizeColumn(3);
        sheet.setColumnWidth(3, 5500); 
        sheet.autoSizeColumn(4);    
        // sheet.setColumnWidth(4, 4500); 

        // Записываем всё в файл
        
        // TODO string url
        File filePath = new File(configUrl.OUT);
        filePath.mkdir();
        SimpleDateFormat dateF = new SimpleDateFormat ("yyyy-MM-dd");
        File filePath2 = new File(filePath + "\\" + dateF.format(new Date()));
        filePath2.mkdir();
        File file = new File(filePath2 + "\\" + configUrl.NAME_TOTAL_LIST + ".xls");    
        try (FileOutputStream outFile = new FileOutputStream(file)) {
            book.write(outFile);
        } catch (IOException e) {
            LOG.error("error I/O in Total list\n" + e.getMessage());
        } finally {
            try { 
                book.close();
            } catch (IOException e) {
                LOG.error("error 2 I/O in Total list\n" + e.getMessage());
            } 
        }
    }

    private void createRowTitle() {
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(styles.get("Title"));
        cell.setCellValue(config.TITLE);
        cell.getRow().setHeight((short) 1000);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,4)); 
    }

    private void createRowHeadersTable() {
        String arrRow5[] = { config.COLUMN_0, 
                             config.COLUMN_1,
                             config.COLUMN_2,
                             config.COLUMN_3,
                             config.COLUMN_4 };
        HSSFRow row = sheet.createRow(2);
        for (int i = 0; i < 5; i++) {
            HSSFCell cell = row.createCell(i); 
            cell.setCellStyle(styles.get("HeaderTotalTable"));      
            cell.setCellValue(arrRow5[i]);
        }
    }

    private void createRowTotalSum() {        
        int numberRow = startRowData + countRecord + 1;
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
        cellTotalSum.setCellValue(totalSum);
    }

    private void createRowSignature() {
        int numberRow = startRowData + countRecord + 4;
        HSSFRow rowTake = sheet.createRow(numberRow);
        HSSFCell cellSignature = rowTake.createCell(0);
        cellSignature.setCellValue(config.SIGNATURE_TOP);		
        HSSFCell cellUnderline = rowTake.createCell(1);
        cellUnderline.setCellStyle(styles.get("Uderline"));
        
    }

    private void createRowSignatureDate() {
        int numberRow = startRowData + countRecord + 7;
        HSSFRow rowTake = sheet.createRow(numberRow);
        HSSFCell cellSignature = rowTake.createCell(0);
        cellSignature.setCellValue(config.SIGNATURE_BOTTOM);
        HSSFCell cellUnderline = rowTake.createCell(1);
        cellUnderline.setCellStyle(styles.get("Uderline"));
        
    }

    public void addRowRecordData(Data data) {
        countRecord++;
        totalSum += data.getTotalSum();
        HSSFRow row = sheet.createRow(startRowData + countRecord);
        HSSFCell cellNumber = row.createCell(0);
        cellNumber.setCellStyle(styles.get("DataCenterTable"));
        cellNumber.setCellValue(countRecord);
        HSSFCell cellAddress = row.createCell(1);
        cellAddress.setCellStyle(styles.get("DataCenterTable"));
        cellAddress.setCellValue(data.getClientAdr());
        HSSFCell cellClientId = row.createCell(2);
        cellClientId.setCellStyle(styles.get("DataCenterTable"));
        cellClientId.setCellValue(data.getClientId());
        HSSFCell cellName = row.createCell(3);
        cellName.setCellStyle(styles.get("DataCenterTable"));
        cellName.setCellValue(data.getClientName());
        HSSFCell cellSum = row.createCell(4);
        cellSum.setCellStyle(styles.get("DataRightTable"));
        cellSum.setCellValue(data.getTotalSum());        
    }

}
package debtors;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class StyleTable {
    // private static final Logger LOG = LoggerFactory.getLogger(StyleTable.class);

    public static HSSFCellStyle createBorder(HSSFCellStyle style) {        
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }

    private static HSSFCellStyle createFont(HSSFWorkbook book, HSSFCellStyle style, 
                                            short height, boolean bold, short color){
        HSSFFont font = book.createFont();
        font.setBold(bold);
        font.setColor(color);
        font.setFontHeightInPoints(height);
        style.setFont(font);
        return style;
    }

    private static HSSFCellStyle createFont(HSSFWorkbook book, HSSFCellStyle style, 
                                            short height,  boolean bold){
        return createFont(book, style, height, bold, IndexedColors.BLACK.getIndex());
    }

    public static HSSFCellStyle createStyleUderline(HSSFWorkbook book) {
        HSSFCellStyle style = book.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }

    public static HSSFCellStyle createStyleDate(HSSFWorkbook book) {
        HSSFCellStyle style = book.createCellStyle();
        style = createFont(book, style, (short) 9, false);
        HSSFDataFormat format = book.createDataFormat();        
        style.setDataFormat(format.getFormat("dd.mm.yyyy"));
        return style;
    }

    public static HSSFCellStyle createStyleTitle(HSSFWorkbook book) {
        HSSFCellStyle style = book.createCellStyle();
        style = createFont(book, style, (short) 12, true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        return style;
    }

    public static HSSFCellStyle createStyleSubtitle(HSSFWorkbook book) {
        HSSFCellStyle style = book.createCellStyle();
        style = createFont(book, style, (short) 9, true);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }


    public static HSSFCellStyle createStyleHeaderTable(HSSFWorkbook book) {
        HSSFCellStyle style = book.createCellStyle();
        style = createFont(book, style, (short) 8, true, IndexedColors.BROWN.getIndex());
        style.setAlignment(HorizontalAlignment.CENTER);
        style = createBorder(style);
        return style;
    }
    
    public static HSSFCellStyle createStyleHeaderTotalTable(HSSFWorkbook book) {
        HSSFCellStyle style = book.createCellStyle();
        style = createFont(book, style, (short) 10, true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style = createBorder(style);
        return style;
    }

    public static HSSFCellStyle createStyleDataLeftTable(HSSFWorkbook book) {
        HSSFCellStyle style = book.createCellStyle();
        style = createFont(book, style, (short) 9, false);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style = createBorder(style);
        return style;
    }

    public static HSSFCellStyle createStyleDataCenterTable(HSSFWorkbook book) {
        HSSFCellStyle style = book.createCellStyle();
        style = createFont(book, style, (short) 9, false);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style = createBorder(style);
        return style;
    }

    public static HSSFCellStyle createStyleDataRightTable(HSSFWorkbook book) {
        HSSFCellStyle style = book.createCellStyle();
        style = createFont(book, style, (short) 9, false);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style = createBorder(style);
        return style;
    }

    public static HSSFCellStyle createStyleTotalSum(HSSFWorkbook book) {
        HSSFCellStyle style = book.createCellStyle();
        style = createFont(book, style, (short) 9, true);
        style = createBorder(style);       
        return style;
    }

    
}
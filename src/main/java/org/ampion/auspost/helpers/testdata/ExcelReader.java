package org.ampion.auspost.helpers.testdata;

import com.creditdatamw.zerocell.Reader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static org.ampion.auspost.Constants.*;

public class ExcelReader {
    public <T> List<T> read(String fileName, String sheetName, Class<T> c) {
        return load(fileName, sheetName, c)
                .list();
    }

    private <T> Reader.@NotNull ReaderBuilder<T> load(String fileName, String sheetName, Class<T> c) {
        return Reader.of(c)
                .from(new File(TEST_DATA + fileName))
                .sheet(sheetName)
                .skipHeaderRow(true);
    }

    public static @NotNull List<Object> read(String fileName, String sheetName, String testcaseName) {
        ArrayList<Object> arrayList = new ArrayList<>();
        XSSFSheet sheet = getSheet(fileName, sheetName);
        if (sheet != null) {
            Iterator<Row> rows = sheet.rowIterator();
            Row firstRow = rows.next();
            Iterator<Cell> cells = firstRow.cellIterator();
            int k = 0;
            int column = 0;
            while (cells.hasNext()) {
                Cell cell = cells.next();
                if (cell.getStringCellValue().equalsIgnoreCase("TestCases")) {
                    column = k;
                }
                k++;
            }
            while (rows.hasNext()) {
                Row row = rows.next();
                if (row.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (cell.getCellType() == CellType.STRING) {
                            arrayList.add(cell.getStringCellValue());
                        } else if (cell.getCellType() == CellType.NUMERIC) {
                            arrayList.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static @NotNull Object[][] read(String fileName, String sheetName) {
        XSSFSheet sheet = getSheet(fileName, sheetName);
        Object[][] excelArray = new Object[0][];
        if (sheet != null) {
            int rowCount = sheet.getLastRowNum();
            int columnCount = sheet.getRow(0).getLastCellNum();
            excelArray = new Object[rowCount][1];
            for (int i = 0; i < rowCount; i++) {
                Map<Object, Object> data = new HashMap<>();
                for (int j = 0; j < columnCount; j++) {
                    String heading = sheet.getRow(0).getCell(j).getStringCellValue();
                    DataFormatter dataFormatter = new DataFormatter();
                    String value = dataFormatter.formatCellValue(sheet.getRow(i + 1).getCell(j));
                    data.put(heading, value);
                }
                excelArray[i][0] = data;
            }
        }
        return excelArray;
    }

    private static FileInputStream readFile(String fileName) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(TEST_DATA + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fis;
    }

    private static XSSFSheet getSheet(String fileName, String sheetName) {
        XSSFSheet sheet = null;
        try (XSSFWorkbook wb = new XSSFWorkbook(readFile(fileName))) {
            int sheets = wb.getNumberOfSheets();
            for (int i = 0; i < sheets; i++) {
                if (wb.getSheetName(i).equalsIgnoreCase(sheetName)) {
                    sheet = wb.getSheetAt(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet;
    }
}
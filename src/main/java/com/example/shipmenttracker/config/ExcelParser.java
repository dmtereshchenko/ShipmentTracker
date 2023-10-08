package com.example.shipmenttracker.config;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelParser {

    public static List<String> parse(String fileName, int sheetIndex) {
        List<String> data = new ArrayList<>();
        XSSFWorkbook workbook;
        try (InputStream stream = new FileInputStream(fileName)) {
            workbook = new XSSFWorkbook(stream);
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Iterator<Row> it = sheet.iterator();
            while (it.hasNext()) {
                String result = "";
                Row row = it.next();
                Iterator<Cell> cells = row.iterator();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case STRING:
                            result += cell.getStringCellValue() + "\n";
                            break;
                        case NUMERIC:
                            cell.setCellType(CellType.STRING);
                            result += cell.getStringCellValue() + "\n";
                            break;
                    }
                }
                data.add(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

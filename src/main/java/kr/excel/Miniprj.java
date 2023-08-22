package kr.excel;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.*;
import java.util.Date;

public class Miniprj {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream(new File("src\\main\\resources\\sample\\example.xlsx"));
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet) {
                for(Cell cell: row) {
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            if(DateUtil.isCellDateFormatted(cell)) {
                                Date dateValue = cell.getDateCellValue();
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String fomattedDate = dateFormat.format(dateValue);
                                System.out.print(fomattedDate + "\t");
                            } else {
                                double numbericValue = cell.getNumericCellValue();
                                if(numbericValue == Math.floor(numbericValue)) {
                                    int intValue = (int) numbericValue;
                                    System.out.print(intValue + "\t");
                                } else {
                                    System.out.print(numbericValue + "\t");
                                }
                            }
                            break;

                        case STRING:
                            String stringValue = cell.getStringCellValue();
                            System.out.print(stringValue + "\t");
                            break;

                        case BOOLEAN:
                            Boolean booleanValue = cell.getBooleanCellValue();
                            System.out.print(booleanValue + "\t");
                            break;

                        case FORMULA:
                            String formula = cell.getCellFormula();
                            System.out.print(formula + "\t");
                            break;

                        case BLANK:
                            System.out.print("\t");
                            break;

                        default:
                            System.out.print("\t");
                            break;
                    }
                }
                System.out.println();
            }
            file.close();
            System.out.println("\n" + "Excel Data 읽어오기 성공!!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

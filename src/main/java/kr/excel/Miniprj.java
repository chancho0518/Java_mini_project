package kr.excel;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Miniprj {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream(new File("src\\main\\resources\\sample\\example.xlsx"));
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet) {
                for(Cell cell: row) {
                    System.out.print(cell.toString() + "\t" );
                }
                System.out.println();
            }
            file.close();
            System.out.println("Excel Data 읽어오기 성공!!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

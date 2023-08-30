package kr.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcelWriter {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<MemberVO> members = new ArrayList<>();

        while (true) {
            // 이름에 quit를 입력하면 입력 종료
            System.out.print("이름을 입력하세요: ");
            String name = scanner.nextLine();
            if(name.equals("quit")) {
                break;
            }

            System.out.print("나이를 입력하세요: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("생년월일를 입력하세요: ");
            String birthdate = scanner.nextLine();

            System.out.print("전화번호를 입력하세요: ");
            String phone = scanner.nextLine();

            System.out.print("주소를 입력하세요: ");
            String address = scanner.nextLine();

            System.out.print("결혼여부를 입력하세요(true/false): ");
            Boolean isMarried = scanner.nextBoolean();
            scanner.nextLine();

            MemberVO member = new MemberVO(name, age, birthdate, phone, address, isMarried);
            members.add(member);
        }

        scanner.close();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("members");

            // 해더 생성
            Row headRow = sheet.createRow(0);
            headRow.createCell(0).setCellValue("Name");
            headRow.createCell(1).setCellValue("Age");
            headRow.createCell(2).setCellValue("Bithdate");
            headRow.createCell(3).setCellValue("Phone");
            headRow.createCell(4).setCellValue("Address");
            headRow.createCell(5).setCellValue("Marriage");

            // 데이터 생성
            for(int i=0; i<members.size(); i++) {
                MemberVO member = members.get(i);
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(member.getName());
                row.createCell(1).setCellValue(member.getAge());
                row.createCell(2).setCellValue(member.getBirthdate());
                row.createCell(3).setCellValue(member.getPhone());
                row.createCell(4).setCellValue(member.getAddress());
                row.createCell(5).setCellValue(member.isMarried());
            }

            // 엑셀 파일 저장
            String fileName = "src/main/resources/sample/member.xlsx";
            FileOutputStream outputStream = new FileOutputStream(new File(fileName));
            workbook.write(outputStream);
            workbook.close();

            System.out.println("엑셀 파일이 저장되었습니다. " + fileName);

        } catch (IOException e) {
            System.out.println("엑셀 파일 저장 중에 문제가 발생하였습니다.");
            e.printStackTrace();
        }
    }
}

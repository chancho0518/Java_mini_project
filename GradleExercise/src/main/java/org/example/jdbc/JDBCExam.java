package org.example.jdbc;

import java.sql.*;

public class JDBCExam {

    /**
     * 1,000원에 구매한 group singer를 구하는 SELECT문을 JDBC로 실행
     * Group_singer에 새로운 singer "르세라핌"을 넣어 INSERT문을 JDBC로 실행
     */

    // RDB, mySQL 접근 정보
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JDBC_exercise";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "************";

    public static void main(String[] args) {


        try(// Connection 생성
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            ) {

            // 1,000원에 구매한 group singer를 구하는 SELECT문을 JDBC로 실행
            String stringSQL1 =
                    "SELECT * " +
                    "FROM group_singer G " +
                    "   JOiN buy_history B " +
                    "   ON G.mem_id = B.mem_id " +
                    "WHERE price > 1000; ";

            ResultSet resultSet = statement.executeQuery(stringSQL1);

            while (resultSet.next()) {
                String memId =  resultSet.getString("mem_id");
                String memName = resultSet.getString("mem_name");
                long price = resultSet.getLong("price");

                System.out.println("mem_id: " + memId + "\t"
                        + "mem_name: " + memName + "\t"
                        + "price: " + price);
            }

            // Group_singer에 새로운 singer "르세라핌"을 넣어 INSERT문을 JDBC로 실행
            String stringSQL2 = "INSERT INTO group_singer (mem_id, mem_name, mem_number, addr, phone, height, debut_date) "
                    + "VALUES "
                    + "('M1212', '르세라핌', 5, '서울', '0101231234', 172, '2022-12-06')";

            statement.executeUpdate(stringSQL2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

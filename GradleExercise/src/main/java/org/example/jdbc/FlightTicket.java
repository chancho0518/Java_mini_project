package org.example.jdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

public class FlightTicket {

    // MySQL DB 연결 정보
    private static final String DB_URL = "jdbc:mysql://localhost:3306/flight_ticket_reservation";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "************";

    public static void main(String[] args) {

        // SQL 구문 정의
        String sqlQuery1 = "SELECT user_id, user_name, like_travel_place "
                + " FROM users"
                + "     WHERE user_name = ?";
        String sqlQuery2 = "SELECT ticket_type, departure_loc, arrival_loc, departure_at, return_at, total_price "
                + " FROM airline_ticket"
                + "     WHERE departure_loc = '서울' AND ticket_type = '왕복' AND arrival_loc = ?"
                + "     ORDER BY total_price";

        System.out.println("User 이름을 입력하세요: ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();

        try(// Connection 생성
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement1 = connection.prepareStatement(sqlQuery1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(sqlQuery2);
            ) {

            preparedStatement1.setString(1, userName);
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            String place = null;
            if(resultSet1.next()) {
                place = resultSet1.getNString("like_travel_place");
            }

            Optional<String> likeTravelPlace = Optional.ofNullable(place);
            String likePlace = likeTravelPlace.orElseThrow(() -> new RuntimeException());
            System.out.println("\n 선호하는 여행지: " + likePlace + "\n");

            // 확인한 favorPlace로 항공권 동적으로 질의
            preparedStatement2.setString(1, likePlace);
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            while(resultSet2.next()) {
                String ticketType = resultSet2.getNString("ticket_type");
                String departureLoc = resultSet2.getString("departure_loc");
                LocalDate departureAt = resultSet2.getDate("departure_at").toLocalDate();
                String arrivalLoc = resultSet2.getString("arrival_loc");
                LocalDate arrivalAt = resultSet2.getDate("return_at").toLocalDate();

                System.out.println("type: " + ticketType + "\n"
                        + "출발지: " + departureLoc + "\n"
                        + "도착지: " + arrivalLoc + "\n"
                        + "출국시간: " + departureAt + "\n"
                        + "도착시간: " + arrivalAt + "\n");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

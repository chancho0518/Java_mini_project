package kr.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try(// 서버 Socket 생성
            ServerSocket serverSocket = new ServerSocket(1234);
            // 클라이언트 Socket 접속 대기
            Socket clientSocket = serverSocket.accept();
            ) {

            System.out.println("서버가 시작되었습니다.");
            System.out.println("클라이언트가 접속하였습니다.");

            // 클라이언트로 부터 데이터를 받기 위한 InputStream 생성
            InputStream clientInputStream = clientSocket.getInputStream();
            BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(clientInputStream));

            // 클라이언트로 데이터를 보내기 위한 OutputStream 생성
            OutputStream serverOutputStream = clientSocket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(serverOutputStream, true);

            // InputLine;
            String inputLine;

            // 클라이언트로부터 데이터를 읽고 화면에 출력
            while ((inputLine = clientBufferedReader.readLine()) != null) {
                System.out.println("클라이언트의 요청: " + inputLine);

                printWriter.println("서버로 부터 응답이 왔습니다.");
            }
            // 클라이언트에게 응답을 보냄

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

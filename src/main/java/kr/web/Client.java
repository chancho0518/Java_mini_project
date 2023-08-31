package kr.web;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {

        try(// 서버에 연결
            Socket socket = new Socket("localhost", 1234)
            ) {

            // 서버로 데이터를 보내기 위한 OutputStream 생성
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter clientPrintWriter = new PrintWriter(outputStream, true);

            // 서버로 부터 데이터를 받기 위한 InputStream 생성
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            // 서버로 메세지 전송
            clientPrintWriter.println("Client 요청!!!");

            // 서버로 부터 받은 응답
            String response = bufferedReader.readLine();
            System.out.println("서버로 부터 받은 응답: " + response);

            System.out.println("Clinet가 종료되었습니다!!");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package kr.web;

import java.io.*;

public interface SerializeDataExam {

    public static void main(String[] args) {
        // 직렬화
        Customer customer = new Customer("ID1234", "홍길동");
        byte[] serializedData = null;

        try(ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bao);) {

            objectOutputStream.writeObject(customer);
            objectOutputStream.flush();

            serializedData = bao.toByteArray();
            System.out.println("Customer 직렬화: " + new String(serializedData));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 역직렬화
        try(ByteArrayInputStream bio = new ByteArrayInputStream(serializedData);
            ObjectInputStream objectInputStream = new ObjectInputStream(bio);) {

            Customer customer1 = (Customer) objectInputStream.readObject();
            System.out.println("Customer 역직렬화: " + customer1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
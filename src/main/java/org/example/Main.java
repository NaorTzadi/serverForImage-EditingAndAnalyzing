package org.example;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5050)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());

                String receivedData = in.readUTF();
                String[] parsedData = receivedData.split(":");

                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

                if ("CREDENTIALS".equals(parsedData[0])) {
                    String username = parsedData[1];
                    String password = parsedData[2];

                    //System.out.println(parsedData[1]);
                    //System.out.println(parsedData[2]);

                    // Your code to store username and password, maybe in a database or some data structure
                }

                if ("ADDITIONAL_DATA".equals(parsedData[0])) {
                    String personalDataStr = parsedData[1];
                    String customDataStr = parsedData[2];
                    String isMemberStr = parsedData[3];

                    File[] personalData = gson.fromJson(personalDataStr, File[].class);
                    File[] customData = gson.fromJson(customDataStr, File[].class);
                    boolean isMember = Boolean.parseBoolean(isMemberStr);

                    // Your code to store additional user data
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

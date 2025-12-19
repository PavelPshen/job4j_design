package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String answer = "";
                    String inputLine = input.readLine();
                    if (inputLine.contains("msg=")) {
                        String request = inputLine;
                        String massage = request.substring(request.indexOf("msg=") + 4, request.indexOf(" HTTP"));
                        System.out.println(massage);
                        switch (massage) {
                            case "Hello" -> {
                                answer = "Hello";
                            }
                            case "Exit" -> {
                                answer = "Server is closing";
                                server.close();
                            }
                            default -> {
                                answer = massage;
                            }
                        }
                    }
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    output.write(answer.getBytes());
                    output.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}
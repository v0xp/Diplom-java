package ru.netology.javacore;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    int port;
    Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                ) {
                    String inTask = in.readLine();
                    JsonObject jsonObject = JsonParser.parseString(inTask).getAsJsonObject();
                    String type = jsonObject.get("type").getAsString();
                    String task = jsonObject.get("task").getAsString();
                    if (type.equals("ADD")) {
                        todos.addTask(task);
                    } else if (type.equals("REMOVE")) {
                        todos.removeTask(task);
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
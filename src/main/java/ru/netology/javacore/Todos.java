package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {

    public ArrayList<String> listTask = new ArrayList<>();

    public ArrayList<String> getListTasks() {
        return listTask;
    }

    public void setListTask(ArrayList<String> listTasks) {
        this.listTask = listTasks;
    }


    public void addTask(String task) {
        listTask.add(task);
    }

    public void removeTask(String task) {
        listTask.remove(task);
    }

    public String getAllTasks() {
        Collections.sort(listTask);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listTask.size(); i++) {
            sb.append(listTask.get(i) + " ");
        }
        return sb.toString();
    }

}
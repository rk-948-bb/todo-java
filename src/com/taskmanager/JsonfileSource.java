package com.taskmanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JsonfileSource implements IfileSource {

    private final File file;

    public JsonfileSource(String path) {
        this.file = new File(path);
    }

    @Override
    public List<Task> load() {
        String data = null;
        try {
            data = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Task> tasks = new ArrayList<>();
        data = data.trim();
        data = data.substring(1, data.length() - 1);
        String[] arr = data.split("},");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1)
                arr[i] += "}";
            String id = extract(arr[i], "id");
            Long Id = Long.valueOf(id);
            String title = extract(arr[i], "title");
            String description = extract(arr[i], "description");
            Status status = Status.valueOf(extract(arr[i], "status"));
            Task task = new Task(Id, title, description, status);
            tasks.add(task);
        }

        return tasks;
    }

    @Override
    public void save(List<Task> tasks) {
        StringBuilder sb;
        sb = new StringBuilder("[");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (i > 0) {
                sb.append(",\n");
            }
            sb.append("{\n")
                    .append("\"id\":\"").append(task.getId()).append("\",\n")
                    .append("\"title\":\"").append(task.getTitle()).append("\",\n")
                    .append("\"description\":\"").append(task.getDescription()).append("\",\n")
                    .append("\"status\":\"").append(task.getStatusAsString()).append("\"\n")
                    .append("}\n");
        }

        sb.append("]");
        String data = sb.toString();
        try {
            Files.writeString(file.toPath(), data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String extract(String str, String key) {
        String newKey = "\"" + key + "\"";
        int index = str.indexOf(newKey);
        if (index == -1)
            return "";
        int startIndex = str.indexOf('"', index + newKey.length());

        if (startIndex == -1)
            return "";

        int endIndex = str.indexOf('"', startIndex + 1);

        if (endIndex == -1)
            return "";


        String value = str.substring(startIndex + 1, endIndex);
        return value;
    }
}

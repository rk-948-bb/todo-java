package com.taskmanager;
import java.util.List;

public interface IfileSource {

        List<Task> load();

        void save(List<Task> tasks);
    }


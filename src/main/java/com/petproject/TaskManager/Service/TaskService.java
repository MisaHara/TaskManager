package com.petproject.TaskManager.Service;

import com.petproject.TaskManager.Model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}

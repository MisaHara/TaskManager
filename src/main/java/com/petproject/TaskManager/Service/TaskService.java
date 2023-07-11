package com.petproject.TaskManager.Service;

import com.petproject.TaskManager.Model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public List<Task> getAllTasks() {
        return tasks;
    }
    public Task getTaskById(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }


    public void addTask(Task task) {
        task.setId(String.valueOf(nextId++));
        tasks.add(task);
    }
    public void updateTask(Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId().equals(updatedTask.getId())) {
                task.setName(updatedTask.getName());
                task.setDescription(updatedTask.getDescription());
                task.setPriority(updatedTask.getPriority());
                task.setStatus(updatedTask.getStatus());
                return;
            }
        }
    }

    public void deleteTask(String id) {
        Task taskToRemove = null;
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                taskToRemove = task;
                break;
            }
        }
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            updateNextId();
        }
    }

    private void updateNextId() {
        if (tasks.isEmpty()) {
            nextId = 1;
        } else {
            int maxId = Integer.MIN_VALUE;
            for (Task task : tasks) {
                int taskId = Integer.parseInt(task.getId());
                if (taskId > maxId) {
                    maxId = taskId;
                }
            }
            nextId = maxId + 1;
        }
    }


}

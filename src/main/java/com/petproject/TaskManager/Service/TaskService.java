package com.petproject.TaskManager.Service;

import com.petproject.TaskManager.Model.Task;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<Task> searchTasks(String keyword, String id, String status) {
        List<Task> searchResults = new ArrayList<>();

        // Фильтрация задач на основе ключевого слова (названия)
        if (keyword != null && !keyword.isEmpty()) {
            List<Task> keywordMatches = tasks.stream()
                    .filter(task -> task.getName().contains(keyword))
                    .collect(Collectors.toList());
            searchResults.addAll(keywordMatches);
        }

        // Фильтрация задач по ID
        if (id != null && !id.isEmpty()) {
            Task taskById = getTaskById(id);
            if (taskById != null) {
                searchResults.add(taskById);
            }
        }

        // Фильтрация задач по статусу
        if (status != null && !status.isEmpty()) {
            List<Task> statusMatches = tasks.stream()
                    .filter(task -> task.getStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
            searchResults.addAll(statusMatches);
        }

        return searchResults;
    }

}

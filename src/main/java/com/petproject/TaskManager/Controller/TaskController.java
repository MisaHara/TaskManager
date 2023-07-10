package com.petproject.TaskManager.Controller;

import com.petproject.TaskManager.Model.Task;
import com.petproject.TaskManager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {


    @Autowired
    private TaskService taskService;


    @GetMapping("/tasks")
    public String getAllTasks(Model model){
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/create-task")
    public String createTaskForm(Model model){
        model.addAttribute("task", new Task());
        return "task-form";
    }
    @GetMapping("/update-task")
    public String updateTaskForm(Model model){
        return "update-form";
    }

    @PostMapping("/create-task")
    public String createTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }
}

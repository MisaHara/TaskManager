package com.petproject.TaskManager.Controller;

import com.petproject.TaskManager.Model.Task;
import com.petproject.TaskManager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@Validated
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
    @GetMapping("/update-task/{id}")
    public String updateTaskForm(@PathVariable String id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "update-form";
    }

    @PostMapping("/create-task")
    public String createTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit-task/{id}")
    public String editTaskForm(@PathVariable String id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "edit-form";
    }

    @PostMapping("/update-task")
    public String updateTask(@ModelAttribute Task task) {
        taskService.updateTask(task);
        return "redirect:/tasks";
    }


    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/search")
    public String searchTasks(@RequestParam(name = "keyword", required = false) String keyword,
                              @RequestParam(name = "id", required = false) String id,
                              @RequestParam(name = "status", required = false) String status,
                              Model model) {
        List<Task> searchResults = taskService.searchTasks(keyword, id, status);
        model.addAttribute("searchResults", searchResults);
        return "search-results";
    }
}


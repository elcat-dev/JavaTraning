package com.ertc.taskman.controllers;

import com.ertc.taskman.entities.Task;
import com.ertc.taskman.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/list")
    public String showAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks_list";
    }

    @GetMapping("/new")
    public String showAddTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "new_task_form";
    }

    @PostMapping("/creator")
    public String taskCreation(@ModelAttribute("task") Task task) {
        task.setStatus(Task.Status.CREATED);
        taskService.addTask(task);
        return "creation_result";
    }

}

package com.ertc.taskman.controllers;

import com.ertc.taskman.entities.Task;
import com.ertc.taskman.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.NoResultException;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping("/list")
    public String showTasks(Model model, @ModelAttribute("task") Task task){
        List<Task> tasks = taskService.getTasks(task.getId(), task.getExecutor(), task.getStatus());
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", task);
        model.addAttribute("pStstus", Task.Status.values());
        return "tasks_list";
    }

    @GetMapping("/new")
    public String showAddTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "task_form";
    }

    @PostMapping("/operation")
    public String taskCreation(@ModelAttribute("task") Task task) {
        if(task.getId() == null) {
            task.setStatus(Task.Status.CREATED);
            Long newTaskId = taskService.addTask(task);
        }
        else{
            try {
                taskService.updTask(task);
            }
            catch (NoResultException e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
            }
        }
        return "redirect:/tasks/list";
    }

    @GetMapping("/info/{id}")
    public String showInfoTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTasks(id, null, null).get(0);
        model.addAttribute("task", task);
        return "info_task_form";
    }

    @PostMapping("/del/{id}")
    public String taskDeletion(@PathVariable Long id) {
        taskService.delTask(id);
        return "redirect:/tasks/list";
    }

    @PostMapping("/change/{id}")
    public String showChangeTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTasks(id, null, null).get(0);
        model.addAttribute("task", task);
        model.addAttribute("pStstus", Task.Status.values());
        return "task_form";
    }

}

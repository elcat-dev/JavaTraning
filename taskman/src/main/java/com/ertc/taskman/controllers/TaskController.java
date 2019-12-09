package com.ertc.taskman.controllers;

import com.ertc.taskman.entities.Task;
import com.ertc.taskman.services.TaskService;
import com.ertc.taskman.services.TaskStatusService;
import com.ertc.taskman.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;
    private TaskStatusService statusService;
    private UserService userService;

    @Autowired
    public TaskController(TaskService taskService
            ,TaskStatusService statusService
            ,UserService userService
    ) {
        this.taskService = taskService;
        this.statusService = statusService;
        this.userService = userService;
    }

    @RequestMapping("/list")
    public String showTasks(Model model, @ModelAttribute("task") Task task){
        List<Task> tasks = taskService.getTasks(task.getId(), task.getExecutor(), task.getStatus());
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", task);
        model.addAttribute("pStstus", statusService.getAll());
        model.addAttribute("pExecutors", userService.getUser(null, 2L));
        return "tasks_list";
    }

    @GetMapping("/new")
    public String showAddTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        model.addAttribute("pExecutors", userService.getUser(null, 2L));
        model.addAttribute("pOwner", userService.getUser(null, 1L));
        return "task_form";
    }

    @PostMapping("/operation")
    public String taskCreation(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult, Model model) {
        if(bindingResult.getErrorCount() > 1 ||
                (bindingResult.getErrorCount() == 1 &&
                (bindingResult.hasFieldErrors("status") && task.getId() != null
                 || !bindingResult.hasFieldErrors("status"))
        )){
            model.addAttribute("id", task.getId());
            model.addAttribute("task", task);
            model.addAttribute("pExecutors", userService.getUser(null, 2L));
            model.addAttribute("pOwner", userService.getUser(null, 1L));
            if(task.getId() != null) {
                model.addAttribute("pStstus", statusService.getAll());
            }
            return "task_form";
        }
        if(task.getId() == null) {
            task.setStatus(statusService.getById(1L));
        }
        Long taskId;
        taskId = taskService.saveTask(task);
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
        model.addAttribute("pStstus", statusService.getAll());
        model.addAttribute("pExecutors", userService.getUser(null, 2L));
        model.addAttribute("pOwner", userService.getUser(null, 1L));
        return "task_form";
    }

}

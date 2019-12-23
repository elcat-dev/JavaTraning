package com.ertc.taskmangwt.server.controllers;

import com.ertc.taskmangwt.server.services.TaskService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.ertc.taskmangwt.common.*;
import com.ertc.taskmangwt.server.entities.Task;
import com.ertc.taskmangwt.server.services.StatusService;
import com.ertc.taskmangwt.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private TaskService taskService;
    private StatusService statusService;
    private UserService userService;

    @Autowired
    public TaskController(TaskService taskService
            , StatusService statusService
            , UserService userService
    ) {
        this.taskService = taskService;
        this.statusService = statusService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable Long id) {
        Task task = taskService.getTasks(id, null, null, null, null).get(0);
        TaskDto taskDto = new TaskDto(task.getId()
                ,task.getName()
                ,task.getOwner().getName()
                ,task.getExecutor().getName()
                ,task.getDescription()
                ,task.getStatus().getName()
        );
        return taskDto;
    }

    @GetMapping
    public List<TaskDto> getTasksListDto(@RequestParam(required = false) String executorId
            ,@RequestParam(required = false) String statusId
    ) {
        Long execId = null;
        if(executorId != null){
            execId = Long.parseLong(executorId);
        }
        Long stId = null;
        if(statusId != null){
            stId = Long.parseLong(statusId);
        }
        List<TaskDto> tasks = taskService.getTasksDto(execId, stId);
        return tasks;
    }

    @PostMapping
    public Long createNewTask(@RequestBody TaskDto taskDto) {
        Task task = new Task();
        if(taskDto.getId() != null) {
            task.setId(taskDto.getId());
        }
        task.setName(taskDto.getName());
        task.setOwner(userService.getUser(Long.parseLong(taskDto.getOwner()), null).get(0));
        task.setExecutor(userService.getUser(Long.parseLong(taskDto.getExecutor()), null).get(0));
        task.setDescription(taskDto.getDescription());
        if(task.getId() == null) {
            task.setStatus(statusService.getById(1L));
        }
        else {
            task.setStatus(statusService.getById(Long.parseLong(taskDto.getStatus())));
        }
        return taskService.saveTask(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.delTask(id);
        return new ResponseEntity<>("Successfully removed", HttpStatus.OK);
    }
}

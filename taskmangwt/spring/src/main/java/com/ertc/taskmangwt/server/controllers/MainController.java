package com.ertc.taskmangwt.server.controllers;

import com.ertc.taskmangwt.server.entities.Task;
import com.ertc.taskmangwt.server.services.TaskService;
import com.ertc.taskmangwt.server.services.TaskStatusService;
import com.ertc.taskmangwt.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
//@CrossOrigin
public class MainController {
    private TaskService taskService;
    private TaskStatusService statusService;
    private UserService userService;

    @Autowired
    public MainController(TaskService taskService
            ,TaskStatusService statusService
            ,UserService userService
    ) {
        this.taskService = taskService;
        this.statusService = statusService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<Task> getTasks() {
        List<Task> tasks = taskService.getTasks(null, null, null);
        return tasks;
    }

//    @GetMapping("/items")
//    public List<ItemDto> getAllItems() {
//        List<ItemDto> items = itemService.findAll();
//        return items;
//    }
//
//    @GetMapping("/items/remove/{id}")
//    public ResponseEntity<String> removeItems(@PathVariable Long id) {
//        itemService.remove(id);
//        return new ResponseEntity<String>("Successfully removed", HttpStatus.OK);
//    }
//
//    @PostMapping("/items")
//    public ItemDto createNewItem(@RequestParam Long id, @RequestParam String title) {
//        return itemService.save(new ItemDto(id, title));
//    }
}
package com.ertc.taskmangwt.server.controllers;

import com.ertc.taskmangwt.common.*;
import com.ertc.taskmangwt.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{roleId}")
    public List<UserDto> getUserDto(@PathVariable Long roleId) {
        List<UserDto> users = userService.getUserDto(roleId);
        return users;
    }

}

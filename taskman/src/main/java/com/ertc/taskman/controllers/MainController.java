package com.ertc.taskmangwt.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    // GET http://localhost:8189/app/
    @RequestMapping(value = "/")
    public String showHomePage() {
        return "index";
    }
}

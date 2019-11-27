package com.ertc.taskman.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    // GET http://localhost:8189/app/
    @RequestMapping(value = "/")
    public String showHomePage() {
        return "index";
    }

}

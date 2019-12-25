package com.ertc.taskmangwt.server.controllers;

import com.ertc.taskmangwt.common.*;
import com.ertc.taskmangwt.server.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/status")
public class StatusController {
    private StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<StatusDto> getStatusDto() {
        List<StatusDto> statuses = statusService.getAllDto();
        return statuses;
    }

}

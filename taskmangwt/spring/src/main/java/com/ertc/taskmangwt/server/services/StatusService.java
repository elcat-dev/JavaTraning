package com.ertc.taskmangwt.server.services;

import com.ertc.taskmangwt.common.StatusDto;
import com.ertc.taskmangwt.server.entities.Status;
import com.ertc.taskmangwt.server.repositories.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {
    private TaskStatusRepository repository;

    @Autowired
    public void setRepository(TaskStatusRepository repository) {
        this.repository = repository;
    }

    public List<Status> getAll() {
        return repository.findAll();
    }

    public Status getById(Long id) {
        return repository.findById(id).get();
    }

    public List<StatusDto> getAllDto(){
        List<Status> statuses = getAll();
        List<StatusDto> statusesDto = statuses.stream().map(status -> {
            StatusDto stsDto = new StatusDto();
            stsDto.setId(status.getId());
            stsDto.setName(status.getName());
            return stsDto;
        }).collect(Collectors.toList());
        return statusesDto;
    }

}

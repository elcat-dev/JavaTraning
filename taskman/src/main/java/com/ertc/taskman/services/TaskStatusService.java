package com.ertc.taskman.services;

import com.ertc.taskman.entities.TaskStatus;
import com.ertc.taskman.repositories.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusService {
    private TaskStatusRepository repository;

    @Autowired
    public void setRepository(TaskStatusRepository repository) {
        this.repository = repository;
    }

    public List<TaskStatus> getAll() {
        return repository.findAll();
    }

    public TaskStatus getById(Long id) {
        return repository.findById(id).get();
    }

}

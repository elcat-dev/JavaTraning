package com.ertc.taskmangwt.server.services;

import com.ertc.taskmangwt.common.TaskDto;
import com.ertc.taskmangwt.server.entities.Task;
import com.ertc.taskmangwt.server.entities.Status;
import com.ertc.taskmangwt.server.entities.User;
import com.ertc.taskmangwt.server.repositories.TaskRepository;
import com.ertc.taskmangwt.server.repositories.specifications.TaskSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepository repository;

    @Autowired
    public void setRepository(TaskRepository repository) {
        this.repository = repository;
    }

    public Long saveTask(Task task){
        return repository.save(task).getId();
    }

    public List<Task> getTasks(
            Long id
            , User executor
            , Status status
            , Long executorId
            , Long statusId
    ){
        Specification<Task> spec = Specification.where(null);
        if (id != null) {
            spec = spec.and(TaskSpecifications.idIs(id));
        }
        if (executor != null) {
            spec = spec.or(TaskSpecifications.executorIs(executor.getId()));
        }
        if (status != null) {
            spec = spec.or(TaskSpecifications.statusIs(status.getId()));
        }
        if (executorId != null) {
            spec = spec.or(TaskSpecifications.executorIs(executorId));
        }
        if (statusId != null) {
            spec = spec.or(TaskSpecifications.statusIs(statusId));
        }
        return repository.findAll(spec);
    }

    public List<TaskDto> getTasksDto(Long executorId, Long statusId){
        List<Task> tasks = getTasks(null, null, null, executorId, statusId);
        List<TaskDto> taskLists = tasks.stream().map(task -> {
            TaskDto tlDto = new TaskDto();
            tlDto.setId(task.getId());
            tlDto.setName(task.getName());
            tlDto.setExecutor(task.getExecutor().getName());
            tlDto.setStatus(task.getStatus().getName());
            return tlDto;
        }).collect(Collectors.toList());
        return taskLists;
    }

    public void delTask(Long id){
        repository.deleteById(id);
    }

}

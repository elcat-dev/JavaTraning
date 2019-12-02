package com.ertc.taskman.services;

import com.ertc.taskman.entities.Task;
import com.ertc.taskman.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository repository;

    @Autowired
    public void setRepository(TaskRepository repository) {
        this.repository = repository;
    }

    public Long addTask(Task task){
        return repository.addTask(task);
    }

    public List<Task> getTasks(Long id, String executor, Task.Status status){
        return repository.getTasks(id, executor, status);
    }

    public void delTask(Long id){
        repository.delTaskById(id);
    }

    public void updTask(Task uTask) throws RuntimeException{
            repository.updTask(uTask);
    }

}

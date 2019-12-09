package com.ertc.taskman.services;

import com.ertc.taskman.entities.Task;
import com.ertc.taskman.entities.TaskStatus;
import com.ertc.taskman.entities.User;
import com.ertc.taskman.repositories.TaskRepository;
import com.ertc.taskman.repositories.specifications.TaskSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Task> getTasks(Long id, User executor, TaskStatus status){
        Specification<Task> spec = Specification.where(null);
        if (id != null) {
            spec = spec.and(TaskSpecifications.idIs(id));
        }
        if (executor != null) {
            spec = spec.or(TaskSpecifications.executorIs(executor.getId()));
        }
        if (status != null) {
            spec = spec.or(TaskSpecifications.statusIs(status.getStatusId()));
        }
        return repository.findAll(spec);
    }

    public void delTask(Long id){
        repository.deleteById(id);
    }

}

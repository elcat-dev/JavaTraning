package com.ertc.taskman.services;

import com.ertc.taskman.entities.Task;
import com.ertc.taskman.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class TaskService {
    private TaskRepository repository;

    @Autowired
    public void setRepository(TaskRepository repository) {
        this.repository = repository;
    }

    public void addTask(Task task){
        repository.addTask(task);
    }

    public List<Task> getAllTasks(){
        return repository.getTasks();
    }

    public void delTask(Long id){
        boolean checkDel = repository.delTaskById(id);
//        System.out.print("Task id = " + id + " ");
//        if (!checkDel) {
//            System.out.print("not ");
//        }
//        System.out.print("deleted");
    }

    public void updTask(long id, String name, String executor, String description, Task.Status status){
        try {
            Task uTask = repository.getTasksById(id);
            uTask.updTask(name, executor, description, status);
            repository.updTask(uTask);
//            System.out.println("Task id = " + id + " updated");
        }
        catch (NoResultException e){
            System.out.println(e.getMessage());
        }
    }

}

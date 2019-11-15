package com.ertc.taskman;

import com.ertc.taskman.exceptions.NoSuchTaskException;
import com.ertc.taskman.exceptions.RepositorySpaceException;
import com.ertc.taskman.exceptions.TaskAlreadyExistsException;

public class TaskService {
    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void addTask(Task task){
        try {
            repository.addTask(task);
            System.out.println("Task added");
        } catch (RepositorySpaceException | TaskAlreadyExistsException e) {
            System.out.println("Task not added: " + e.toString());
        }
    }

    public void printTaskRep(){
        System.out.println("Task Repository: " + repository.getTitle() + " ( size = " + repository.getTasks().size() + " )");
        for (Task taskArr: repository.getTasks()) {
            System.out.println(taskArr.toString());
        }
    }

    public void delTask(Long id){
        try {
            repository.getTasks().remove(repository.findTaskById(id));
            System.out.println("Task id = " + id + " deleted");
        } catch (NoSuchTaskException e) {
            System.out.println("Task not deleted: " + e.toString());
        }
    }

    public void updTask(long id, String name, String executor, String description, Task.Status status){
        try {
            repository.updTask(id, name, executor, description, status);
        } catch (NoSuchTaskException e) {
            System.out.println("Task not updated: " + e.toString());
        }

    }

}

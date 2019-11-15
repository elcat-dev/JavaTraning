package com.ertc.taskman;

import com.ertc.taskman.exceptions.NoSuchTaskException;
import com.ertc.taskman.exceptions.RepositorySpaceException;
import com.ertc.taskman.exceptions.TaskAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements RepService{
    private String title;
    private List<Task> tasks;

    public String getTitle() {
        return title;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public TaskRepository(String title) {
        prepare(title);
    }

    @Override
    public void prepare(String title){
        this.title = title;
        this.tasks = new ArrayList<>();
    }

    @Override
    public boolean addTask(Task task){
        if( !isTaskExists(task) ){
            return addTaskExc(task);
        }
        return false;
    }

    @Override
    public boolean addTask(long id, String name, String owner, String executor, String description){
        Task task = new Task(id, name, owner, executor, description);
        if( !isTaskExists(task) ){
            return addTaskExc(task);
        }
        return false;
    }

    private boolean addTaskExc(Task task){
        if(tasks.size() == 10){
            throw new RepositorySpaceException("Repository Full");
        }
        tasks.add(task);
        return true;
    }

    @Override
    public boolean isTaskExists(Task task){
        for (Task listTask: tasks) {
            if(listTask.equals(task)){
                throw new TaskAlreadyExistsException("Task Already Exists");
            }
        }
        return false;
    }

    @Override
    public Task findTaskById(Long id){
        for (Task listTask: tasks) {
            if(id.equals(listTask.getId())){
                return listTask;
            }
        }
        throw new NoSuchTaskException("No Such Task");
    }

    private int findIndexByTaskId(Long id){
        for (int i = 0; i < tasks.size() ; i++) {
            if(id.equals(tasks.get(i).getId())){
                return i;
            }
        }
        throw new NoSuchTaskException("No Such Task");
    }

    @Override
    public boolean updTask(long id, String name, String executor, String description, Task.Status status){
        Task uTask = this.findTaskById(id);
        int indexTask = this.findIndexByTaskId(id);
        uTask.updTask(name, executor, description, status);
        tasks.set(indexTask, uTask);
        return true;
    }
}

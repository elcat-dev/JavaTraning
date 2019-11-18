package com.ertc.taskman;

import com.ertc.taskman.exceptions.DirectoryIsEmptyException;
import com.ertc.taskman.exceptions.NoSuchTaskException;
import com.ertc.taskman.exceptions.RepositorySpaceException;
import com.ertc.taskman.exceptions.TaskAlreadyExistsException;

import java.io.*;
import java.util.List;

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

    public Task findTaskById(Long id){
        for (Task listTask: repository.getTasks()) {
            if(id.equals(listTask.getId())){
                return listTask;
            }
        }
        throw new NoSuchTaskException("No Such Task");
    }

    public void printTaskRep(){
        System.out.println("Task Repository: " + repository.getTitle() + " ( size = " + repository.getTasks().size() + " )");
        for (Task taskArr: repository.getTasks()) {
            System.out.println(taskArr.toString());
        }
    }

    public void delTask(Long id){
        try {
            repository.getTasks().remove(findTaskById(id));
            System.out.println("Task id = " + id + " deleted");
        } catch (NoSuchTaskException e) {
            System.out.println("Task not deleted: " + e.toString());
        }
    }

    public void updTask(long id, String name, String executor, String description, Task.Status status){
        try {
            Task uTask = findTaskById(id);
            uTask.updTask(name, executor, description, status);
            repository.updTask(uTask);
        } catch (NoSuchTaskException e) {
            System.out.println("Task not updated: " + e.toString());
        }
    }

    public void getTaskByStatus(Task.Status status){
        repository.getTasks().stream()
                .filter(t -> t.getStatus() == status)
                .forEach(System.out::println);
    }

    public boolean isTaskExistsById(Long id){
        return repository.getTasks().stream().anyMatch(t -> t.getId() == id);
    }

    public void getTaskOrderByStatus(){
        repository.getTasks().stream()
                .sorted((t1, t2) -> t1.getStatus().getIndex() - t2.getStatus().getIndex() )
                .forEach(System.out::println);
    }

    public void getCountTaskByStatus(Task.Status status){
        System.out.println(
                repository.getTasks().stream()
                        .filter(t -> t.getStatus() == status)
                        .count()
        );
    }

    private boolean checkInOutTask(Long[] arrId, Long taskId){
        boolean check  = (arrId.length == 0);
        if(!check) {
            for (int i = 0; i < arrId.length; i++) {
                if(arrId[i].equals(taskId)) {
                    return true;
                }
            }
        }
        return check;
    }

    public void exportTasks(Long[] arrId){
        for (Task taskArr: repository.getTasks()) {
            if(checkInOutTask(arrId, taskArr.getId())){
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("task_storage/" + taskArr.getId()))){
                    out.writeObject(taskArr);
                    System.out.println("file '" + taskArr.getId() + "' saved");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void importTasks(Long[] arrId){
        File taskStorage = new File("task_storage");
        if(taskStorage.list().length == 0){
            throw new DirectoryIsEmptyException("The task store is empty");
        }
        for (String fileName: taskStorage.list()) {
            if(checkInOutTask(arrId, Long.parseLong(fileName))){
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("task_storage/" + fileName))){
                    Task taskIn = (Task) in.readObject();
                    if(isTaskExistsById(taskIn.getId())){
                        updTask(taskIn.getId(), taskIn.getName(), taskIn.getExecutor(), taskIn.getDescription(), taskIn.getStatus());
                        System.out.println("task '" + taskIn.getId() + "' updated from file");
                    }
                    else {
                        addTask(taskIn);
                        System.out.println("task '" + taskIn.getId() + "' loaded from file");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

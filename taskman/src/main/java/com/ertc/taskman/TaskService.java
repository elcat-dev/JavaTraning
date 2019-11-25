package com.ertc.taskman;

import com.ertc.taskman.exceptions.DirectoryIsEmptyException;
import com.ertc.taskman.exceptions.NoSuchTaskException;
import com.ertc.taskman.exceptions.TaskAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TaskService {
    private TaskRepository repository;

    @Autowired
    public void setRepository(TaskRepository repository) {
        this.repository = repository;
    }

    public void addTask(Task task){
        try {
            repository.addTask(task);
            System.out.println("Task added");
        } catch (TaskAlreadyExistsException e) {
            System.out.println("Task not added: " + e.toString());
        }
    }

    public void printTaskRep(){
        for (Task taskArr: repository.getTasks()) {
            System.out.println(taskArr.toString());
        }
    }

    public void delTask(Long id){
        boolean checkDel = repository.delTaskById(id);
        System.out.print("Task id = " + id + " ");
        if (!checkDel) {
            System.out.print("not ");
        }
        System.out.print("deleted");
    }

    public void updTask(long id, String name, String executor, String description, Task.Status status){
        Task uTask = repository.getTasksById(id);
        uTask.updTask(name, executor, description, status);
        boolean checkUpd = repository.updTask(uTask);
        if (checkUpd) {
            System.out.println("Task id = " + id + " updated");
        }
        else {
            throw new NoSuchTaskException("task id = " + id + " not updated");
        }
    }

    public void getTaskByStatus(Task.Status status){
        repository.getTaskByStatus(status).stream()
                .forEach(System.out::println);
    }

    public boolean isTaskExistsById(Long id){
        return repository.isTaskExistsById(id);
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

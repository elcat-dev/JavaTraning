package com.ertc.taskman;

public interface RepService {
    void prepare(String title);
    boolean addTask(Task task);;
    boolean addTask(long id, String name, String owner, String executor, String description);
    boolean isTaskExists(Task task);
    Task findTaskById(Long id);
    boolean updTask(long id, String name, String executor, String description, Task.Status status);
}

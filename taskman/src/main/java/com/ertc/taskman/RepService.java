package com.ertc.taskman;

public interface RepService {
    void prepare(String title);
    boolean addTask(Task task);;
    boolean addTask(long id, String name, String owner, String executor, String description);
    boolean isTaskExists(Task task);
    boolean updTask(Task uTask);
}

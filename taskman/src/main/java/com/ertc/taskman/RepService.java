package com.ertc.taskman;

import java.util.List;

public interface RepService {
    boolean addTask(Task task);;
    boolean addTask(long id, String name, String owner, String executor, String description);
    boolean isTaskExists(Task task);
    boolean updTask(Task uTask);
    List<Task> getTasks();
    String getTitle();
}

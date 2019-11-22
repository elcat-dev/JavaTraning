package com.ertc.taskman;

import java.util.List;

public class Repository implements RepService{
    @Override
    public boolean addTask(Task task) {
        return false;
    }

    @Override
    public boolean addTask(long id, String name, String owner, String executor, String description) {
        return false;
    }

    @Override
    public boolean isTaskExists(Task task) {
        return false;
    }

    @Override
    public boolean updTask(Task uTask) {
        return false;
    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }
}

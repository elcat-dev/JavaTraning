package com.ertc.taskman;

import java.util.Objects;

public class Task {
    public enum Status {
        CREATED("Created"), CLOSE("Close"), REJECTED("Rejected");

        private String engTitle;

        public String getEngTitle() {
            return engTitle;
        }

        Status(String engTitle) {
            this.engTitle = engTitle;
        }
    }

    private Long id;
    private String name;
    private String owner;
    private String executor;
    private String description;
    private Status status;

    public Task(){
    }

    public Task(long id, String name, String owner, String executor, String description) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.executor = executor;
        this.description = description;
        this.status = Status.CREATED;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (this == obj){
            return true;
        }
        if(!(obj instanceof Task)){
            return false;
        }
        Task task = (Task) obj;
        return this.id.equals(task.id) &&
                this.name.equals(task.name) &&
                this.owner.equals(task.owner) &&
                this.executor.equals(task.executor) &&
                this.description.equals(task.description) &&
                this.status.equals(task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, executor, description, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", executor='" + executor + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status.engTitle + '\'' +
                '}';
    }

    public void updTask(String name, String executor, String description, Task.Status status){
        this.name = name;
        this.executor = executor;
        this.description = description;
        this.status = status;
    }
}

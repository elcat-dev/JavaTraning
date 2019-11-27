package com.ertc.taskman.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
    private static final long serialVersionUID = 4224055084185265651L;

    public enum Status {
        CREATED, CLOSE, REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;
    @Column(name = "t_name")
    private String name;
    @Column(name = "t_owner")
    private String owner;
    @Column(name = "t_executor")
    private String executor;
    @Column(name = "t_description")
    private String description;
    //@OneToOne
    //@JoinColumn(name = "status_id")
    @Enumerated(EnumType.STRING)
    @Column(name = "t_status")
    private Status status;

    public Task(){
    }

    public Task(String name, String owner, String executor, String description) {
        this.name = name;
        this.owner = owner;
        this.executor = executor;
        this.description = description;
        this.status = Status.CREATED;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public Status getStatus() {
        return status;
    }

    public String getExecutor() {
        return executor;
    }

    public String getDescription() {
        return description;
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
        return this.name.equals(task.name) &&
                this.owner.equals(task.owner) &&
                this.executor.equals(task.executor) &&
                this.description.equals(task.description) &&
                this.status.equals(task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, owner, executor, description, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", executor='" + executor + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public void updTask(String name, String executor, String description, Task.Status status){
        this.name = name;
        this.executor = executor;
        this.description = description;
        this.status = status;
    }

}

package com.ertc.taskman;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tasks_hnt")
public class Task implements Serializable {
    private static final long serialVersionUID = 4224055084185265651L;

    public enum Status {
        CREATED("Created", 1)
        ,CLOSE("Close", 2)
        ,REJECTED("Rejected", 3);

        private String engTitle;
        private int index;

        public String getEngTitle() {
            return engTitle;
        }

        public int getIndex() { return index; };

        Status(String engTitle, int index) {
            this.index = index;
            this.engTitle = engTitle;
        }
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
    @Column(name = "t_status")
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

    public boolean comparator(Task t1, Task t2){
        return t1.status.equals(t2.status);
    }

}

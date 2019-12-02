package com.ertc.taskman.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task implements Serializable {
    private static final long serialVersionUID = 4224055084185265651L;

    public enum Status {
        CREATED, CLOSE, REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_task_id")
    @SequenceGenerator(name = "s_task_id", sequenceName = "s_task_id", allocationSize = 1)
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
    @Enumerated(EnumType.STRING)
    @Column(name = "t_status")
    private Status status;

    public Task(String name, String owner, String executor, String description) {
        this.name = name;
        this.owner = owner;
        this.executor = executor;
        this.description = description;
        this.status = Status.CREATED;
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

}

package com.ertc.taskman.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task implements Serializable {
    private static final long serialVersionUID = 4224055084185265651L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_task_id")
    @SequenceGenerator(name = "s_task_id", sequenceName = "s_task_id", allocationSize = 1)
    @Column(name = "task_id")
    private Long id;
    @Column(name = "t_name")
    @Size(min = 1, message = "Task name cannot be empty")
    private String name;

    @OneToOne
    @JoinColumn (name = "owner_id")
    @NotNull(message = "Task owner cannot be empty")
    private User owner;
    @OneToOne
    @JoinColumn (name = "executor_id")
    @NotNull(message = "Task executor cannot be empty")
    private User executor;

    @Column(name = "t_description")
    @Size(min = 1, message = "Task description cannot be empty")
    private String description;
    @OneToOne
    @JoinColumn (name = "status_id")
    @NotNull(message = "Task status cannot be empty")
    private TaskStatus status;

}

package com.ertc.taskmangwt.server.entities;

import javax.persistence.*;
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
    private String name;

    @OneToOne
    @JoinColumn (name = "owner_id")
    private User owner;
    @OneToOne
    @JoinColumn (name = "executor_id")
    private User executor;

    @Column(name = "t_description")
    private String description;
    @OneToOne
    @JoinColumn (name = "status_id")
    private Status status;

}

package com.ertc.taskman.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "task_status")
@Data
@NoArgsConstructor
public class TaskStatus {

    @Id
    @Column(name = "status_id")
    private Long statusId;
    @Column(name = "status_name")
    private String statusName;

}

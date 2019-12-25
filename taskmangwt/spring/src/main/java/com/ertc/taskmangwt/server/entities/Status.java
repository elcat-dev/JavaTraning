package com.ertc.taskmangwt.server.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "task_status")
@Data
@NoArgsConstructor
public class Status {

    @Id
    @Column(name = "status_id")
    private Long id;
    @Column(name = "status_name")
    private String name;

}

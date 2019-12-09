package com.ertc.taskman.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tm_users_roles")
@Data
@NoArgsConstructor
public class UsersRole {

    @Id
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;

}

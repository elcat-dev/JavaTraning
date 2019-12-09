package com.ertc.taskman.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tm_users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_user_id")
    @SequenceGenerator(name = "s_user_id", sequenceName = "s_user_id", allocationSize = 1)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name")
    private String name;
    @OneToOne
    @JoinColumn (name = "role_id")
    private UsersRole role;

}

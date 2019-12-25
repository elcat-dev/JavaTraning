package com.ertc.taskmangwt.server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

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
    @Column(name = "email")
    private  String email;
    @Column(name = "upassword")
    private String password;

    @OneToMany(mappedBy = "owner")
    @JsonBackReference
    private List<Task> tasksOwner;

    @OneToMany(mappedBy = "executor")
    @JsonBackReference
    private List<Task> tasksExecutor;

    @ManyToMany(fetch=FetchType.LAZY)
    @JsonBackReference
    @JoinTable(name = "tm_user_role_links",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<UsersRole> roles;

}

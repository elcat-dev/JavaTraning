package com.ertc.taskmangwt.server.repositories;

import com.ertc.taskmangwt.server.entities.UsersRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UsersRole, Long> {
}

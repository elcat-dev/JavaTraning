package com.ertc.taskman.repositories;

import com.ertc.taskman.entities.UsersRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UsersRole, Long> {
}

package com.ertc.taskmangwt.server.repositories;

import com.ertc.taskmangwt.server.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends JpaRepository<Status, Long> {
}

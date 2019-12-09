package com.ertc.taskman.repositories.specifications;

import com.ertc.taskman.entities.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {
    public static Specification<Task> idIs(Long id){
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Task> statusIs(Long statusId){
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status").get("statusId"), statusId);
    }

    public static Specification<Task> executorIs(Long executorId){
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("executor").get("id"), executorId);
    }
}

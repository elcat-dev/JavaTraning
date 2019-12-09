package com.ertc.taskman.repositories.specifications;

import com.ertc.taskman.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> idIs(Long id){
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<User> roleIs(Long roleId){
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("role").get("roleId"), roleId);
    }
}

package com.ertc.taskmangwt.server.repositories.specifications;

import com.ertc.taskmangwt.server.entities.User;
import com.ertc.taskmangwt.server.entities.UsersRole;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class UserSpecifications {
    public static Specification<User> idIs(Long id){
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<User> roleIs(Long roleId){
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> root.join("roles").in(roleId);
    }
}

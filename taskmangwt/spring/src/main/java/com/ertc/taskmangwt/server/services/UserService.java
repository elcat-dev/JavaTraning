package com.ertc.taskmangwt.server.services;

import com.ertc.taskmangwt.server.entities.User;
import com.ertc.taskmangwt.server.repositories.UserRepository;
import com.ertc.taskmangwt.server.repositories.specifications.UserSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUser(Long id, Long roleId){
        Specification<User> spec = Specification.where(null);
        if (id != null) {
            spec = spec.and(UserSpecifications.idIs(id));
        }
        if (roleId != null) {
            spec = spec.and(UserSpecifications.roleIs(roleId));
        }
        return repository.findAll(spec);
    }
}

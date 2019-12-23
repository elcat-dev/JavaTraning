package com.ertc.taskmangwt.server.services;

import com.ertc.taskmangwt.common.UserDto;
import com.ertc.taskmangwt.server.entities.User;
import com.ertc.taskmangwt.server.repositories.UserRepository;
import com.ertc.taskmangwt.server.repositories.specifications.UserSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserDto> getUserDto(Long roleId){
        List<User> users = getUser(null, roleId);
        List<UserDto> usersDto = users.stream().map(user -> {
            UserDto uDto = new UserDto();
            uDto.setId(user.getId());
            uDto.setName(user.getName());
            return uDto;
        }).collect(Collectors.toList());
        return usersDto;
    }
}

package com.ertc.taskmangwt.server.services;

import com.ertc.taskmangwt.common.UserDto;
import com.ertc.taskmangwt.server.entities.User;
import com.ertc.taskmangwt.server.repositories.UserRepository;
import com.ertc.taskmangwt.server.repositories.specifications.UserSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository repository;
    private PasswordEncoder bcryptEncoder;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setBcryptEncoder(PasswordEncoder bcryptEncoder) {
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findOneByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Collection<? extends GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
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

    public User save(UserDto userDto) {
        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        return repository.save(newUser);
    }
}

package com.annm.zilliqa_project.service.serviceImpl;

import com.annm.zilliqa_project.dto.UserDto;
import com.annm.zilliqa_project.entity.Roles;
import com.annm.zilliqa_project.entity.Users;
import com.annm.zilliqa_project.repository.RoleRepository;
import com.annm.zilliqa_project.repository.UserRepository;
import com.annm.zilliqa_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users save(UserDto userDto) {
        Users users = new Users();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setUsername(userDto.getUsername());
        users.setPassword(userDto.getPassword());
        users.setRoles(Arrays.asList(roleRepository.findByName("USER")));
        return userRepository.save(users);
    }
}

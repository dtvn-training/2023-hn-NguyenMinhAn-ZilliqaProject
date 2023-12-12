package com.annm.zilliqa_project.service.serviceImpl;

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
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }


    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username);
        if(users==null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(),rolesToAuthorities(users.getRoles()));
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Roles> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}

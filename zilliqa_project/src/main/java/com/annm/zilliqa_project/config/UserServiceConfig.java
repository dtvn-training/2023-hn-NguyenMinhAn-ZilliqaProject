package com.annm.zilliqa_project.config;

import com.annm.zilliqa_project.entity.Users;
import com.annm.zilliqa_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class UserServiceConfig implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Could not find user!");
        }
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(roles ->new SimpleGrantedAuthority(roles.getName())).collect(Collectors.toList()));
    }
}

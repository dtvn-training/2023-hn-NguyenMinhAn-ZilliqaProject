package com.annm.zilliqa_project.service;

import com.annm.zilliqa_project.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public Users findByUsername(String username);

    public void save(Users users);
}

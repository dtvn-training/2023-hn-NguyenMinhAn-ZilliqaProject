package com.annm.zilliqa_project.service;

import com.annm.zilliqa_project.dto.UserDto;
import com.annm.zilliqa_project.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    public Users findByUsername(String username);

    public Users save(UserDto userDto);
}
